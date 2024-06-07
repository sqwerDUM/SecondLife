package com.itacademy.finalprod.project_.service.impl;

import com.itacademy.finalprod.project_.dto.ProductDto;
import com.itacademy.finalprod.project_.entity.Product;
import com.itacademy.finalprod.project_.mapper.ProductMapper;
import com.itacademy.finalprod.project_.repo.ProductRepository;
import com.itacademy.finalprod.project_.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .size(productDto.getSize())
                .color(productDto.getColor())
                .build();
        try {
            productRepository.save(product);
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
        }


        return productDto;
    }

    @Override
    public ProductDto findById(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not Found with id " + id));
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .size(product.getSize())
                .color(product.getColor())
                .build();
        return productDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
      return  productMapper.toDtoList(productRepository.findAll());
    }


    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
        log.info("Product with id " + id + " is deleted");
    }



    @Override
    public List<ProductDto> getProductsByColor(String color) {
        List<ProductDto> productDtoList = new ArrayList<>();

        List<Product> allProducts = productRepository.findProductsByColor(color);
        int numThreads = Runtime.getRuntime().availableProcessors();
        int totalProducts = allProducts.size();
        int productsPerThread = totalProducts / numThreads;
        int remainingProducts = totalProducts % numThreads;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int startIndex = 0;
        for (int i = 0; i < numThreads; i++) {
            int endIndex = startIndex + productsPerThread + (i < remainingProducts ? 1 : 0);
            List<Product> productsSubset = allProducts.subList(startIndex, endIndex);
            executor.execute(() -> processProducts(productsSubset, productDtoList));
            startIndex = endIndex;
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return productDtoList;
    }

    @Override
    public List<ProductDto> findProductsByCriteria(double price, List<String> colors) {

            var products = productRepository.findAllByPriceAndColorIn(price, colors);
            return products.parallelStream()
                    .map(product -> ProductDto.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .size(product.getSize())
                            .color(product.getColor())
                            .build())
                    .collect(Collectors.toList());


    }

    private void processProducts(List<Product> products, List<ProductDto> productDtoList) {
        for (Product product : products) {
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .build();
            synchronized (productDtoList) {
                productDtoList.add(productDto);
            }
        }
    }



    @Override
    public void decreaseStockQuantity(Product product, int quantity) {

        int currentStock = product.getQuantity();
        int updatedStock = currentStock - quantity;

        if (updatedStock < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }

        product.setQuantity(updatedStock);
        productRepository.save(product);
    }



}

package com.itacademy.finalprod.project_.service;



import com.itacademy.finalprod.project_.dto.ProductDto;
import com.itacademy.finalprod.project_.entity.Product;

import java.util.List;

public interface ProductService {

   ProductDto createProduct(ProductDto productDto);

    ProductDto findById(Long id);

    List<ProductDto> getAllProducts();

    void deleteProductById(Long id);


    List<ProductDto> getProductsByColor(String color);

    List<ProductDto> findProductsByCriteria(double price, List<String> colors);

    void decreaseStockQuantity(Product product, int quantity);
}

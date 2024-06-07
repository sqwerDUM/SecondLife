package com.itacademy.finalprod.project_.controller;


import com.itacademy.finalprod.project_.dto.ProductDto;
import com.itacademy.finalprod.project_.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class    ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        ProductDto product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/filter")
    public ResponseEntity<List<ProductDto>> getProductsByColor( @RequestParam(required = false) String color) {
        List<ProductDto> products = new ArrayList<>();
        if (color != null) {
            products = productService.getProductsByColor(color);
        }
        return ResponseEntity.ok(products);
    }

     @GetMapping("/products/filterBy")
     public ResponseEntity<List<ProductDto>> getProductsByCriteria(
             @RequestParam("price") double price,
             @RequestParam("colors") List<String> colors
     ) {
         List<ProductDto> products = productService.findProductsByCriteria(price, colors);
         return ResponseEntity.ok(products);
     }

}

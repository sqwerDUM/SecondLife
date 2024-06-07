package com.itacademy.finalprod.project_.repo;


import com.itacademy.finalprod.project_.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.color = :color")
    List<Product> findProductsByColor(@Param("color") String color);

    @Query("""
        SELECT p FROM Product p 
        WHERE p.price > :price 
        AND p.color IN :colors
        """)
    List<Product> findAllByPriceAndColorIn(
            @Param("price") double price,
            @Param("colors") List<String> colors
    );


}

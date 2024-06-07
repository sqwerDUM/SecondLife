package com.itacademy.finalprod.project_.repo;


import com.itacademy.finalprod.project_.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer,Long> {
}

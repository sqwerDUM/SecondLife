package com.itacademy.finalprod.project_.repo;


import com.itacademy.finalprod.project_.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findAllBySalesmanId(Long salesman_id);

    List<Transaction> getAllBySalesmanId(Long id);
}

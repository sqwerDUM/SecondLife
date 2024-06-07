package com.itacademy.finalprod.project_.service;


import com.itacademy.finalprod.project_.dto.TransactionDto;
import com.itacademy.finalprod.project_.entity.Transaction;

import java.util.List;

public interface TransactionService {
     Transaction sellProduct(TransactionDto transactionDto);
    List<Transaction> getAll();
    Transaction getById(Long id);

    List<Transaction> getAllByBuyerId(Long salesmanId);
}
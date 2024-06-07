package com.itacademy.finalprod.project_.controller;


import com.itacademy.finalprod.project_.dto.TransactionDto;
import com.itacademy.finalprod.project_.entity.Transaction;
import com.itacademy.finalprod.project_.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAll() {
        return transactionService.getAll();
    }



    @GetMapping("/buyer/{id}")
    public List<Transaction> getAllByBuyerId(@PathVariable Long id) {
        return transactionService.getAllByBuyerId(id);
    }

    @PostMapping
    public Transaction makeTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.sellProduct(transactionDto);
    }
}

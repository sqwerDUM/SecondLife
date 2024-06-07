package com.itacademy.finalprod.project_.service.impl;


import com.itacademy.finalprod.project_.dto.TransactionDto;
import com.itacademy.finalprod.project_.entity.Buyer;
import com.itacademy.finalprod.project_.entity.Product;
import com.itacademy.finalprod.project_.entity.Transaction;
import com.itacademy.finalprod.project_.exception.NotFoundException;
import com.itacademy.finalprod.project_.repo.BuyerRepository;
import com.itacademy.finalprod.project_.repo.ProductRepository;
import com.itacademy.finalprod.project_.repo.TransactionRepository;
import com.itacademy.finalprod.project_.service.ProductService;
import com.itacademy.finalprod.project_.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final BuyerRepository buyerRepository;
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;
    private final ProductService productService;


    @Override
    public Transaction sellProduct(TransactionDto transactionDto) {
        Buyer buyer = buyerRepository.findById(transactionDto.getBuyerID())
                .orElseThrow(() -> new NotFoundException("Buyer not found with ID: " + transactionDto.getBuyerID()));
        Product product = productRepository.findById(transactionDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + transactionDto.getProductId()));
        if (product.getQuantity() < transactionDto.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock quantity");
        }
        else {
            Transaction transaction = new Transaction();
            transaction.setProduct(product);
            transaction.setBuyer(buyer);
            transaction.setQuantity(transactionDto.getQuantity());
            productService.decreaseStockQuantity(product, transaction.getQuantity());
            return transactionRepository.save(transaction);
        }
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(Long id) {
        return transactionRepository.findById(id).orElseThrow(()->new NotFoundException("Не найдено транзакции с ID : "+ id));
    }

    @Override
    public List<Transaction> getAllByBuyerId(Long salesmanId) {
        return null;
    }
}

package com.itacademy.finalprod.project_.controller;


import com.itacademy.finalprod.project_.dto.BuyerDto;
import com.itacademy.finalprod.project_.entity.Buyer;
import com.itacademy.finalprod.project_.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/buyers")
public class BuyerController {
    private final BuyerService buyerService;

    @GetMapping
    public ResponseEntity<List<Buyer>> getAllBuyers() {
        List<Buyer> buyers = buyerService.getAll();
        return ResponseEntity.ok(buyers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getById(@PathVariable Long id) {
        Buyer buyer = buyerService.getById(id);
        return ResponseEntity.ok(buyer);
    }

    @PostMapping
    public ResponseEntity<BuyerDto> createBuyer(@RequestBody BuyerDto buyerDto) {
        BuyerDto createdBuyer = buyerService.save(buyerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBuyer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        buyerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

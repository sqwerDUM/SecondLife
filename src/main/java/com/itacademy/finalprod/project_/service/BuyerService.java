package com.itacademy.finalprod.project_.service;


import com.itacademy.finalprod.project_.dto.BuyerDto;
import com.itacademy.finalprod.project_.entity.Buyer;

import java.util.List;

public interface BuyerService {

    BuyerDto save(BuyerDto buyerDto);

    List<Buyer> getAll();

    Buyer getById(Long id);

    void deleteById(Long id);
}

package com.itacademy.finalprod.project_.service.impl;


import com.itacademy.finalprod.project_.dto.BuyerDto;
import com.itacademy.finalprod.project_.entity.Buyer;
import com.itacademy.finalprod.project_.exception.NotFoundException;
import com.itacademy.finalprod.project_.mapper.BuyerMapper;
import com.itacademy.finalprod.project_.repo.BuyerRepository;
import com.itacademy.finalprod.project_.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    private final BuyerRepository buyerRepository;
    private final BuyerMapper buyerMapper;

    @Override
    public BuyerDto save(BuyerDto buyerDto) {
        Buyer buyer = buyerRepository.save(buyerMapper.toEntity(buyerDto));
        return buyerMapper.toDto(buyer);
    }

    @Override
    public List<Buyer> getAll() {
        return buyerRepository.findAll();
    }

    @Override
    public Buyer getById(Long id) {
        return buyerRepository.findById(id).orElseThrow(() -> new NotFoundException("Покупателя с ID " + id + " не найдено"));
    }

    @Override
    public void deleteById(Long id) {
        buyerRepository.deleteById(id);
    }
}

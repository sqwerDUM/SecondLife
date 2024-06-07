package com.itacademy.finalprod.project_.mapper;


import com.itacademy.finalprod.project_.dto.BuyerDto;
import com.itacademy.finalprod.project_.entity.Buyer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface BuyerMapper {
    BuyerDto toDto(Buyer buyer);

    Buyer toEntity(BuyerDto buyerDto);
}

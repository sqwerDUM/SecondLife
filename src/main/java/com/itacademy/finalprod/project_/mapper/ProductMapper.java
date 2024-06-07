package com.itacademy.finalprod.project_.mapper;


import com.itacademy.finalprod.project_.dto.ProductDto;
import com.itacademy.finalprod.project_.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<ProductDto> toDtoList(List<Product> product);
}

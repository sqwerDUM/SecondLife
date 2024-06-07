package com.itacademy.finalprod.project_.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDto {
    private Long id;
    private String name;
    private String surName;
}

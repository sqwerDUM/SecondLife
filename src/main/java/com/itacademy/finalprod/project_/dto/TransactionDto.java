package com.itacademy.finalprod.project_.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
   private Long buyerID;
   private Long productId;
   private int quantity;
}

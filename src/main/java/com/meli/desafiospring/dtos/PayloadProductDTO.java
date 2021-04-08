package com.meli.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayloadProductDTO {
    private int productId;
    private String name;
    private String brand;
    private int quantity;

}

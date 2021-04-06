package com.meli.desafiospring.dtos;

import lombok.Data;

@Data
public class ProductDTO {
    private int productId;
    private String name;
    private String category;
    private String brand;
    private double price;
    private int quantity;
    private boolean freeShipping;
    private double prestige;
}

package com.meli.desafiospring.repositories;

import com.meli.desafiospring.dtos.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public interface ProductsRepo {

    List<ProductDTO> findByCategory(String category);
    List<ProductDTO> findAll();

    List<ProductDTO> findByProductName(String productName);

    List<ProductDTO> findByBrand(String brand);

    List<ProductDTO> findByPrice(double price);

    List<ProductDTO> findByFreeShipping(boolean freeShipping);

    List<ProductDTO> findByPrestige(double prestige);
}

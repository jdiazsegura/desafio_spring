package com.meli.desafiospring.services;

import com.meli.desafiospring.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductDTO> findAll();

    List<ProductDTO> findByCategory(String category);

    List<ProductDTO> findByProductName(String productName);

    List<ProductDTO> findByBrand(String brand);

    List<ProductDTO> findByPrice(double price);

    List<ProductDTO> findByFreeShipping(boolean freeShipping);

    List<ProductDTO> findByPrestige(double prestige);

    List<ProductDTO> getMethod(Map<String, String> allParams);
}

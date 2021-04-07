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

    //FILTER METHODS
    List<ProductDTO> filterByCategory(String category, List<ProductDTO> list);

    List<ProductDTO> filterByFreeShipping(Boolean freeShipping, List<ProductDTO> list);

    List<ProductDTO> filterByBrand(String brand, List<ProductDTO> list);

    List<ProductDTO> filterByPrice(Double price, List<ProductDTO> list);

    List<ProductDTO> filterByPrestige(Double prestige, List<ProductDTO> list);

    List<ProductDTO> filterByName(String name, List<ProductDTO> list);
    List<ProductDTO> sortAlphAsc(List<ProductDTO> list);

    List<ProductDTO> sortAlphDesc(List<ProductDTO> list);

    List<ProductDTO> sortByPriceAsc(List<ProductDTO> list);

    List<ProductDTO> sortByPriceDesc(List<ProductDTO> list);
}

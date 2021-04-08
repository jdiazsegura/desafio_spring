package com.meli.desafiospring.repositories;

import com.meli.desafiospring.dtos.PayloadProductDTO;
import com.meli.desafiospring.dtos.ProductDTO;
import com.meli.desafiospring.exceptions.CategoryNotFoundException;
import com.meli.desafiospring.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface ProductsRepo {

    //FIND METHODS
    List<ProductDTO> findAll();

    List<ProductDTO> findByCategory(String category) throws  CategoryNotFoundException;

    List<ProductDTO> findByProductName(String productName) throws ProductNotFoundException;

    List<ProductDTO> findByBrand(String brand) throws ProductNotFoundException;

    List<ProductDTO> findByPrice(double price) throws ProductNotFoundException;

    List<ProductDTO> findByFreeShipping(boolean freeShipping) throws ProductNotFoundException;

    List<ProductDTO> findByPrestige(double prestige) throws ProductNotFoundException;

    List<ProductDTO> findById(int id) throws ProductNotFoundException;

    //FILTER METHODS
    List<ProductDTO> filterByCategory(String category, List<ProductDTO> list) throws  CategoryNotFoundException;

    List<ProductDTO> filterByFreeShipping(Boolean freeShipping, List<ProductDTO> list) throws ProductNotFoundException;

    List<ProductDTO> filterByBrand(String brand, List<ProductDTO> list) throws ProductNotFoundException;

    List<ProductDTO> filterByPrice(Double price, List<ProductDTO> list) throws ProductNotFoundException;

    List<ProductDTO> filterByPrestige(Double prestige, List<ProductDTO> list) throws ProductNotFoundException;

    List<ProductDTO> filterByName(String name, List<ProductDTO> list) throws ProductNotFoundException;

    //SORT METHODS
    List<ProductDTO> sortAlphAsc(List<ProductDTO> list);

    List<ProductDTO> sortAlphDesc(List<ProductDTO> list);

    List<ProductDTO> sortByPriceAsc(List<ProductDTO> list);

    List<ProductDTO> sortByPriceDesc(List<ProductDTO> list);

    List<ProductDTO> normaliceProducts(List<PayloadProductDTO> list) throws ProductNotFoundException;

    List<PayloadProductDTO> normalicePayProducts(List<ProductDTO> list);
}

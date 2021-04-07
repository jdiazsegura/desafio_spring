package com.meli.desafiospring.services;

import com.meli.desafiospring.dtos.ProductDTO;
import com.meli.desafiospring.repositories.ProductsRepo;
import com.meli.desafiospring.repositories.ProductsRepoImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    ProductsRepo productsRepo;

    public ProductServiceImpl(ProductsRepo productsRepo) {
        this.productsRepo = productsRepo;
    }

    @Override
    public List<ProductDTO> findAll(){
        return productsRepo.findAll();
    }

    @Override
    public List<ProductDTO> findByCategory(String category){
        return productsRepo.findByCategory(category);
    }

    @Override
    public List<ProductDTO> findByProductName(String productName){
        return productsRepo.findByProductName(productName);
    }

    @Override
    public List<ProductDTO> findByBrand(String brand){
        return productsRepo.findByBrand(brand);
    }

    @Override
    public List<ProductDTO> findByPrice(double price){
        return productsRepo.findByPrice(price);
    }

    @Override
    public List<ProductDTO> findByFreeShipping(boolean freeShipping){
        return productsRepo.findByFreeShipping(freeShipping);
    }

    @Override
    public List<ProductDTO> findByPrestige(double prestige){
        return productsRepo.findByPrestige(prestige);
    }

    @Override
    public List<ProductDTO> getMethod(Map<String, String> allParams){
        List<ProductDTO> list = new ArrayList<>();
        List<ProductDTO> result = new ArrayList<>();
        if (allParams.isEmpty()) {
            return findAll();
        } else {
            for (var param : allParams.keySet()) {
                var value = allParams.get(param);
                switch (param) {
                    case "category":
                        result = productsRepo.filterByCategory(value,list);
                        break;
                    case "freeShipping":
                        result = productsRepo.filterByFreeShipping(Boolean.parseBoolean(value),list);
                        break;
                    case "brand":
                        result = productsRepo.filterByBrand(value,list);
                        break;
                    case "price":
                        result = productsRepo.filterByPrice(Double.parseDouble(value),list);
                        break;
                    case "prestige":
                        result = productsRepo.filterByPrestige(Double.parseDouble(value),list);
                        break;
                    case "name":
                        result = productsRepo.filterByName(value,list);
                        break;
                    case "order":
                        switch (value) {
                            case "0":
                                return productsRepo.sortAlphAsc(result);
                            case "1":
                                return productsRepo.sortAlphDesc(result);
                            case "2":
                                return productsRepo.sortByPriceAsc(result);
                            case "3":
                                return productsRepo.sortByPriceDesc(result);
                        }
                }
            }
        }
        return result;
    }
}

package com.meli.desafiospring.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafiospring.dtos.ProductDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductsRepoImpl implements ProductsRepo {

    private final List<ProductDTO> database ;

    public ProductsRepoImpl() {
        this.database = loadDataBase();
    }

    public ArrayList<ProductDTO> findAll(){
        List<ProductDTO> ProductsDTOS = null;
        ProductsDTOS = loadDataBase();
        ArrayList<ProductDTO> result = new ArrayList<>();
        if(ProductsDTOS != null){
            for(var x : ProductsDTOS){
                result.add(x);
            }
        }
        return result;
    }

    @Override
    public List<ProductDTO> findByCategory(String category) {
        return database.stream()
                .filter(ProductDTO -> matchWith(category,ProductDTO.getCategory()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByProductName(String productName) {
        return database.stream()
                .filter(ProductDTO -> matchWith(productName,ProductDTO.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByBrand(String brand) {
        return database.stream()
                .filter(ProductDTO -> matchWith(brand,ProductDTO.getBrand()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByPrice(double price) {
        return database.stream()
                .filter(ProductDTO -> matchWith(price,ProductDTO.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByFreeShipping(boolean freeShipping) {
        return database.stream()
                .filter(ProductDTO -> matchWith(freeShipping, ProductDTO.isFreeShipping()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByPrestige(double prestige) {
        return database.stream()
                .filter(ProductDTO -> matchWith(prestige,ProductDTO.getPrestige()))
                .collect(Collectors.toList());
    }


    private boolean matchWith(String query, String string) {
        return string.toUpperCase().equals(query.toUpperCase());
    }

    private boolean matchWith(Double value, Double value2) {
        return value == value2;
    }

    private boolean matchWith(boolean value, boolean value2) {
         var flag = value == value2;
        return flag;
    }

    private List<ProductDTO> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:Products.json");
            //file = ResourceUtils.
        }catch (Exception e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ProductDTO>> typeRef = new TypeReference<List<ProductDTO>>() {};
        List<ProductDTO> priceDTOS = null;

        try {
            priceDTOS = objectMapper.readValue(file, typeRef);
        }catch (Exception e){
            e.printStackTrace();
        }

        return priceDTOS;
    }
}

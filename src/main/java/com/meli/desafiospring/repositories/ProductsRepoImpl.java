package com.meli.desafiospring.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafiospring.dtos.PayloadProductDTO;
import com.meli.desafiospring.dtos.ProductDTO;
import com.meli.desafiospring.exceptions.CategoryNotFoundException;
import com.meli.desafiospring.exceptions.ProductNotFoundException;
import com.meli.desafiospring.exceptions.QuantityNotEnoughException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductsRepoImpl implements ProductsRepo {

    // LOAD DATABASE
    private final List<ProductDTO> database ;

    public ProductsRepoImpl() {
        this.database = loadDataBase();
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

    //  FIND METHODS
    @Override
    public List<ProductDTO> findAll(){
        List<ProductDTO> result = new ArrayList<>();
        if(database != null){
            result.addAll(database);
        }
        return result;
    }

    @Override
    public List<ProductDTO> findByCategory(String category) throws  CategoryNotFoundException {
        var result = database.stream()
                .filter(ProductDTO -> matchWith(category,ProductDTO.getCategory()))
                .collect(Collectors.toList());
        if(result.isEmpty()){
            throw new CategoryNotFoundException(category);
        }else {
            return result;
        }

    }

    @Override
    public List<ProductDTO> findByProductName(String productName) throws ProductNotFoundException {
        var result = database.stream()
                .filter(ProductDTO -> matchWith(productName,ProductDTO.getName()))
                .collect(Collectors.toList());
        if (result.isEmpty()){
            throw new ProductNotFoundException(productName);
        }else{
            return result;
        }
    }

    @Override
    public List<ProductDTO> findByBrand(String brand) throws ProductNotFoundException {
        var result =  database.stream()
                .filter(ProductDTO -> matchWith(brand,ProductDTO.getBrand()))
                .collect(Collectors.toList());
        if (result.isEmpty()){
            throw new ProductNotFoundException(brand);
        }else {
            return result;
        }
    }

    @Override
    public List<ProductDTO> findByPrice(double price) throws ProductNotFoundException {
        var result = database.stream()
                .filter(ProductDTO -> matchWith(price,ProductDTO.getPrice()))
                .collect(Collectors.toList());
        if(result.isEmpty()){
            throw new ProductNotFoundException(String.valueOf(price));
        }else {
            return result;
        }
    }

    @Override
    public List<ProductDTO> findByFreeShipping(boolean freeShipping) throws ProductNotFoundException {
        var result = database.stream()
                .filter(ProductDTO -> matchWith(freeShipping, ProductDTO.isFreeShipping()))
                .collect(Collectors.toList());
        if(result.isEmpty()){
            throw new ProductNotFoundException(String.valueOf(freeShipping));
        }else {
            return result;
        }
    }

    @Override
    public List<ProductDTO> findByPrestige(double prestige) throws ProductNotFoundException {
        var result = database.stream()
                .filter(ProductDTO -> matchWith(prestige,ProductDTO.getPrestige()))
                .collect(Collectors.toList());
        if(result.isEmpty()){
            throw new ProductNotFoundException(String.valueOf(prestige));
        }else {
            return result;
        }
    }

    @Override
    public List<ProductDTO> findById(int id) throws ProductNotFoundException {
        var result = database.stream()
                .filter(ProductDTO -> matchWith(id,ProductDTO.getProductId()))
                .collect(Collectors.toList());
        if (result.isEmpty()){
            throw new ProductNotFoundException(String.valueOf(id));
        }else {
            return result;
        }
    }

    // MATCH METHODS
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

    private boolean matchWith(int value, int value2) {
        return value == value2;
    }

    //FILTER METHODS
    @Override
    public List<ProductDTO> filterByCategory(String category, List<ProductDTO> list) throws  CategoryNotFoundException {
        if (list.isEmpty()) {
            list.addAll(findByCategory(category));
        } else {
            list.removeIf(element -> !element.getCategory().equals(category));
        }
        return list;
    }

    @Override
    public List<ProductDTO> filterByFreeShipping(Boolean freeShipping, List<ProductDTO> list) throws ProductNotFoundException {
        if (list.isEmpty()) {
            list.addAll(findByFreeShipping(freeShipping));
        } else {
            list.removeIf(element -> element.isFreeShipping() != freeShipping);
        }
        return list;
    }

    @Override
    public List<ProductDTO> filterByBrand(String brand, List<ProductDTO> list) throws ProductNotFoundException {
        if (list.isEmpty()) {
            list.addAll(findByBrand(brand));
        } else {
            list.removeIf(element -> !element.getBrand().equals(brand));
        }
        return list;
    }

    @Override
    public List<ProductDTO> filterByPrice(Double price, List<ProductDTO> list) throws ProductNotFoundException {
        if (list.isEmpty()) {
            list.addAll(findByPrice(price));
        } else {
            list.removeIf(element -> true);
        }
        return list;
    }

    @Override
    public List<ProductDTO> filterByPrestige(Double prestige, List<ProductDTO> list) throws ProductNotFoundException {
        if (list.isEmpty()) {
            list.addAll(findByPrestige(prestige));
        } else {
            list.removeIf(element -> true);
        }
        return list;
    }

    @Override
    public List<ProductDTO> filterByName(String name, List<ProductDTO> list) throws ProductNotFoundException {
        if (list.isEmpty()) {
            list.addAll(findByProductName(name));
        } else {
            list.removeIf(element -> !element.getName().equals(name));
        }
        return list;
    }


    //SORT METHODS
    @Override
    public List<ProductDTO> sortAlphAsc(List<ProductDTO> list){
        List<ProductDTO> sortedList;
        sortedList = list.stream().sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<ProductDTO> sortAlphDesc(List<ProductDTO> list){
        List<ProductDTO> sortedList;
        sortedList = list.stream().sorted(Comparator.comparing(ProductDTO::getName)
                .reversed())
                .collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<ProductDTO> sortByPriceAsc(List<ProductDTO> list){
        List<ProductDTO> sortedByPrice;
        sortedByPrice = list.stream().sorted(Comparator.comparingDouble(ProductDTO::getPrice))
                .collect(Collectors.toList());
        return sortedByPrice;
    }

    @Override
    public List<ProductDTO> sortByPriceDesc(List<ProductDTO> list){
        List<ProductDTO> sortedByPrice;
        sortedByPrice = list.stream().sorted(Comparator.comparingDouble(ProductDTO::getPrice)
                .reversed())
                .collect(Collectors.toList());
        return sortedByPrice;
    }

    @Override
    public List<ProductDTO> normaliceProducts(List<PayloadProductDTO> list) throws ProductNotFoundException {
        List<ProductDTO> result = new ArrayList<>();
        for (var element:list){
            var get = findById(element.getProductId());
            for(var elementData: get){
                    var flag = elementData;
                    flag.setQuantity(elementData.getQuantity());
                    result.add(flag);
                }
            }
        return result;
    }

    @Override
    public List<PayloadProductDTO> normalicePayProducts(List<ProductDTO> list){
        List<PayloadProductDTO> result = null;
        for (var element:list){
            result.add(new PayloadProductDTO(element.getProductId(),element.getName(), element.getBrand(), element.getQuantity()));
        }
        return result;
    }

    @Override
    public Boolean stockAvailable(PayloadProductDTO product) throws QuantityNotEnoughException {
        var element = database.get(product.getProductId()-1);
        if (element.getQuantity() >= product.getQuantity()){
            return true;
        }else{
            throw new QuantityNotEnoughException(product.getName());
        }
    }

}

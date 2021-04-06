package com.meli.desafiospring.services;

import com.meli.desafiospring.dtos.ProductDTO;
import com.meli.desafiospring.repositories.ProductsRepo;
import com.meli.desafiospring.repositories.ProductsRepoImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}

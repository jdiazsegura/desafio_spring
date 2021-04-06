package com.meli.desafiospring.controllers;

import com.meli.desafiospring.dtos.ProductDTO;
import com.meli.desafiospring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/articles")
    public ResponseEntity<List> get(@RequestParam Map<String, String> allParams) {
        List<ProductDTO> flag = new ArrayList<>();
        List<ProductDTO> flag2 = new ArrayList<>();
        if (allParams.isEmpty()) {
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        } else {
            for (var param : allParams.keySet()) {
                switch (param) {
                    case "category":
                        if (flag.isEmpty()) {
                            flag.addAll(productService.findByCategory(allParams.get(param)));
                            flag2.addAll(flag);
                        } else {
                            for (ProductDTO element : flag) {
                                if (!element.getCategory().equals(allParams.get(param))) {
                                    flag2.remove(element);
                                }
                            }
                        }
                        break;
                    case "freeShipping":
                        if (flag.isEmpty()) {
                            flag.addAll(productService.findByFreeShipping(Boolean.parseBoolean(allParams.get(param))));
                            flag2.addAll(flag);
                        } else {
                            for (ProductDTO element : flag) {
                                if (!String.valueOf(element.isFreeShipping()).equals(allParams.get(param))) {
                                    flag2.remove(element);
                                }
                            }
                        }
                        break;
                    case "brand":
                        if (flag.isEmpty()) {
                            flag.addAll(productService.findByBrand(allParams.get(param)));
                            flag2.addAll(flag);
                        } else {
                            for (var element : flag) {
                                if (!element.getBrand().equals(allParams.get(param))) {
                                    flag2.remove(element);
                                }
                            }
                        }
                        break;
                    case "price":
                        if (flag.isEmpty()) {
                            flag.addAll(productService.findByPrice(Double.parseDouble(allParams.get(param))));
                            flag2.addAll(flag);
                        } else {
                            for (var element : flag) {
                                if (!String.valueOf(element.getPrice()).equals(allParams.get(param))) {
                                    flag2.remove(element);
                                }
                            }
                        }
                        break;
                    case "prestige":
                        if (flag.isEmpty()) {
                            flag.addAll(productService.findByPrestige(Double.parseDouble(allParams.get(param))));
                            flag2.addAll(flag);
                        } else {
                            for (var element : flag) {
                                if (!String.valueOf(element.getPrestige()).equals(allParams.get(param))) {
                                    flag2.remove(element);
                                }
                            }
                        }
                        break;
                    case "name":
                        if (flag.isEmpty()) {
                            flag.addAll(productService.findByProductName(allParams.get(param)));
                            flag2.addAll(flag);
                        } else {
                            for (var element : flag) {
                                if (!element.getName().equals(allParams.get(param))) {
                                    flag2.remove(element);
                                }
                            }
                        }
                        break;
                    case "order":
                        var element = allParams.get(param);
                        switch (element) {
                            case "0":
                                var sortedList = new ArrayList<ProductDTO>();
                                sortedList = (ArrayList<ProductDTO>) flag2.stream().sorted(Comparator.comparing(ProductDTO::getName))
                                        .collect(Collectors.toList());
                                return new ResponseEntity<>(sortedList,HttpStatus.OK);
                            case "1":
                                var sortedListDesc = new ArrayList<ProductDTO>();
                                sortedListDesc = (ArrayList<ProductDTO>) flag2.stream().sorted(Comparator.comparing(ProductDTO::getName)
                                        .reversed())
                                        .collect(Collectors.toList());
                                return new ResponseEntity<>(sortedListDesc,HttpStatus.OK);
                            case "2":
                                var sortedByPrice = new ArrayList<ProductDTO>();
                                sortedByPrice = (ArrayList<ProductDTO>) flag2.stream().sorted(Comparator.comparingDouble(ProductDTO::getPrice))
                                        .collect(Collectors.toList());
                                return new ResponseEntity<>(sortedByPrice,HttpStatus.OK);
                            case "3":
                                var sortedByPriceAsc = new ArrayList<ProductDTO>();
                                sortedByPriceAsc = (ArrayList<ProductDTO>) flag2.stream().sorted(Comparator.comparingDouble(ProductDTO::getPrice)
                                        .reversed())
                                        .collect(Collectors.toList());
                                return new ResponseEntity<>(sortedByPriceAsc,HttpStatus.OK);

                        }
                }
            }
        }
        return new ResponseEntity<>(flag2, HttpStatus.OK);
    }
}


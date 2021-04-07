package com.meli.desafiospring.controllers;

import com.meli.desafiospring.dtos.PayloadDTO;
import com.meli.desafiospring.dtos.ProductDTO;
import com.meli.desafiospring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/articles")
    public ResponseEntity<List<ProductDTO>> get(@RequestParam Map<String, String> allParams) {
        return new ResponseEntity<>(productService.getMethod(allParams),HttpStatus.OK);
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<PayloadDTO> post(@RequestBody ProductDTO productDTO){
        var flag = new PayloadDTO();
        return new ResponseEntity<>(flag,HttpStatus.OK);
    }
}


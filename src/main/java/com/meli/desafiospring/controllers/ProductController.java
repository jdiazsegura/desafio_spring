package com.meli.desafiospring.controllers;

import com.meli.desafiospring.dtos.*;
import com.meli.desafiospring.exceptions.CategoryNotFoundException;
import com.meli.desafiospring.exceptions.ProductNotFoundException;
import com.meli.desafiospring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ENDPOINTS
    @GetMapping("/articles")
    public ResponseEntity<List<ProductDTO>> get(@RequestParam Map<String, String> allParams) throws ProductNotFoundException, CategoryNotFoundException {
        return new ResponseEntity<>(productService.getMethod(allParams),HttpStatus.OK);
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<PayloadResponseDTO> post(@RequestBody PayloadDTO payloadDTO) throws ProductNotFoundException {
        return new ResponseEntity<>(new PayloadResponseDTO(productService.createTicket(payloadDTO),
                new StatusCodeDTO(200,"Solicitud Completada")),HttpStatus.OK);
    }

    // EXCEPTION HANDLERS
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StatusCodeDTO> ProductNotFoundException(ProductNotFoundException errorException){
        StatusCodeDTO statusCode= new StatusCodeDTO();
        statusCode.setCode(404);
        statusCode.setMessage("Product " + errorException.getMessage()+" not found");
        return new ResponseEntity<>(statusCode, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<StatusCodeDTO> CategoryNotFoundException(CategoryNotFoundException errorException){
        StatusCodeDTO statusCode= new StatusCodeDTO();
        statusCode.setCode(404);
        statusCode.setMessage("Category " + errorException.getMessage()+" not found");
        return new ResponseEntity<>(statusCode, HttpStatus.NOT_FOUND);
    }
}


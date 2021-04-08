package com.meli.desafiospring.services;

import com.meli.desafiospring.dtos.PayloadDTO;
import com.meli.desafiospring.dtos.ProductDTO;
import com.meli.desafiospring.dtos.TicketDTO;
import com.meli.desafiospring.exceptions.CategoryNotFoundException;
import com.meli.desafiospring.exceptions.ProductNotFoundException;
import com.meli.desafiospring.exceptions.QuantityNotEnoughException;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductDTO> getMethod(Map<String, String> allParams) throws ProductNotFoundException, CategoryNotFoundException;

    TicketDTO createTicket(PayloadDTO payloadDTO) throws ProductNotFoundException, QuantityNotEnoughException;
}

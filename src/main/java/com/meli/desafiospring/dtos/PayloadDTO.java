package com.meli.desafiospring.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PayloadDTO {

    private List<PayloadProductDTO> articles;
}

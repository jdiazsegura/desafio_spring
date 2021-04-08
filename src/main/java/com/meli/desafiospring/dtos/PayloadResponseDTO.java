package com.meli.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayloadResponseDTO {
    private TicketDTO ticket;
    private StatusCodeDTO status;
}

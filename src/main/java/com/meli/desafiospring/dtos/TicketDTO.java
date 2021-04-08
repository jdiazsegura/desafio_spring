package com.meli.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class TicketDTO {

    private static int Id = 0;

    private int id;
    private List<PayloadProductDTO> articles;
    private double total;

    public TicketDTO() {
        Id++;
        this.id = Id;
    }
}


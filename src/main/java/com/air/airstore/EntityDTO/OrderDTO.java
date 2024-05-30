package com.air.airstore.EntityDTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private Long id;
    private List<TicketEntityDTO> tickets;
}

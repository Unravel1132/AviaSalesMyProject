package com.air.airstore.EntityDTO;

import com.air.airstore.model.User;
import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Long id;
    private Long userId;
    private List<TicketEntityDTO> tickets;
    private User user;
}
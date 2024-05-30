package com.air.airstore.EntityDTO;

import lombok.Data;

@Data
public class TicketEntityDTO {

    private Long id;
    private String seatNumber;
    private Double price;
    private AirPlaneEntityDTO airPlaneEntity;
}

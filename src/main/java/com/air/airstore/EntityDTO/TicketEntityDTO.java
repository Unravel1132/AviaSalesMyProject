package com.air.airstore.EntityDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TicketEntityDTO {

    private Long id;
    private String title;
    private String seatNumber;
    private Double price;
    @JsonIgnore
    private AirPlaneEntityDTO airPlaneEntity;
}

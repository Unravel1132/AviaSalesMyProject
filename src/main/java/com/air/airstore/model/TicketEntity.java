package com.air.airstore.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNumber;
    private double price;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private AirPlaneEntity airPlaneEntity;
}

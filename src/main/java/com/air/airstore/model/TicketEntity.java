package com.air.airstore.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String seatNumber;
    private double price;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private AirPlaneEntity airPlaneEntity;


    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public TicketEntity(Double price, String title, String seatNumber) {
    }
}

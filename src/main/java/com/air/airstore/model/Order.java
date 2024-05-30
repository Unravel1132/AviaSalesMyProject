package com.air.airstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<TicketEntity> ticketsEntity;
}

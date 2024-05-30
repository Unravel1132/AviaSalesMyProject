package com.air.airstore.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Entity
@Data
public class AirPlaneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "airPlaneEntity")
    private List<TicketEntity> tickets;


}

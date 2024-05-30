package com.air.airstore.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {


    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}

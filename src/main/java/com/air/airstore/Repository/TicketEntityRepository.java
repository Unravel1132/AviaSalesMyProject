package com.air.airstore.Repository;

import com.air.airstore.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TicketEntityRepository extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> SearchTicket(String name, BigDecimal price);
}

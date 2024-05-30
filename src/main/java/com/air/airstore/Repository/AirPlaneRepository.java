package com.air.airstore.Repository;

import com.air.airstore.model.AirPlaneEntity;
import com.air.airstore.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AirPlaneRepository extends JpaRepository<AirPlaneEntity, Long> {
    List<AirPlaneEntity> searchTicket(String name);

}

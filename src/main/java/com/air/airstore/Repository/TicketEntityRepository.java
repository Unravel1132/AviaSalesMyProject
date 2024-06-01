package com.air.airstore.Repository;

import com.air.airstore.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TicketEntityRepository extends JpaRepository<TicketEntity, Long> {

 List<TicketEntity> findByAirPlaneEntityName(String airplaneName);
 }
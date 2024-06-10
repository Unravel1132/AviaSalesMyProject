package com.air.airstore.Repository;

import com.air.airstore.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TicketEntityRepository extends JpaRepository<TicketEntity, Long> {


 @Query("select t from TicketEntity t where t.airPlaneEntity.name = :airplaneName AND t.price = :price")
 List<TicketEntity> findByAirPlaneEntityName(String airplaneName, Double price);


 }
package com.air.airstore.Repository;

import com.air.airstore.model.AirPlaneEntity;
import com.air.airstore.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface AirPlaneRepository extends JpaRepository<AirPlaneEntity, Long> {

    @Query("SELECT a FROM AirPlaneEntity a WHERE :seatCount <= (SELECT COUNT(t) FROM a.tickets t) OR :price <= ALL(SELECT t.price FROM a.tickets t)")
    List<AirPlaneEntity> findBySeatCountOrPrice(@Param("seatCount") int seatCount, @Param("price") double price);
}
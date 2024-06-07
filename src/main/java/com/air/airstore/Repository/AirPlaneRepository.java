package com.air.airstore.Repository;

import com.air.airstore.model.AirPlaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirPlaneRepository extends JpaRepository<AirPlaneEntity, Long> {

    List<AirPlaneEntity> findByName(String name);
    boolean existsByName(String name);
}
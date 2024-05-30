package com.air.airstore.Service.EntitiesService;

import com.air.airstore.EntityDTO.AirPlaneEntityDTO;

import java.util.List;

public interface AirPlaneService {


    List<AirPlaneEntityDTO> getAllAirPlanes();
    AirPlaneEntityDTO getAirPlaneById(Long id);
    AirPlaneEntityDTO createAirPlane(AirPlaneEntityDTO airPlane);
    AirPlaneEntityDTO updateAirPlane(Long id, AirPlaneEntityDTO airPlane);
    void deleteAirPlane(Long id);



}

package com.air.airstore.Service.DTOMapper.ServiceDTO;

import com.air.airstore.EntityDTO.AirPlaneEntityDTO;
import com.air.airstore.model.AirPlaneEntity;

public interface AirPlaneEntityDtoMapper {

    AirPlaneEntityDTO toAirPlaneEntityDTO(AirPlaneEntity airPlaneEntity);
    AirPlaneEntity toAirPlaneEntity(AirPlaneEntityDTO airPlaneEntityDTO);

}

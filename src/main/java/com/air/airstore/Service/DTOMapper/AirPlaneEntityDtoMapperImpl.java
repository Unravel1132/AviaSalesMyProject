package com.air.airstore.Service.DTOMapper;

import com.air.airstore.EntityDTO.AirPlaneEntityDTO;
import com.air.airstore.Service.DTOMapper.ServiceDTO.AirPlaneEntityDtoMapper;
import com.air.airstore.model.AirPlaneEntity;
import org.springframework.stereotype.Service;

@Service
public class AirPlaneEntityDtoMapperImpl implements AirPlaneEntityDtoMapper {
    @Override
    public AirPlaneEntityDTO toAirPlaneEntityDTO(AirPlaneEntity airPlaneEntity) {

            AirPlaneEntityDTO airPlaneEntityDTO = new AirPlaneEntityDTO();
            airPlaneEntityDTO.setId(airPlaneEntity.getId());

        return airPlaneEntityDTO;
    }

    @Override
    public AirPlaneEntity toAirPlaneEntity(AirPlaneEntityDTO airPlaneEntityDTO) {

        AirPlaneEntity airPlaneEntity = new AirPlaneEntity();
        airPlaneEntity.setId(airPlaneEntityDTO.getId());
        return airPlaneEntity;

    }
}

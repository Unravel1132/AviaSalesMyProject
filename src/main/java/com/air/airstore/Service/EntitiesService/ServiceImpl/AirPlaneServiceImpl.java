package com.air.airstore.Service.EntitiesService.ServiceImpl;

import com.air.airstore.EntityDTO.AirPlaneEntityDTO;
import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Repository.AirPlaneRepository;
import com.air.airstore.Service.DTOMapper.ServiceDTO.AirPlaneEntityDtoMapper;
import com.air.airstore.Service.DTOMapper.ServiceDTO.TicketDTOMapper;
import com.air.airstore.Service.EntitiesService.AirPlaneService;
import com.air.airstore.model.AirPlaneEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirPlaneServiceImpl implements AirPlaneService {

    @Autowired
    private AirPlaneRepository airPlaneRepository;
    @Autowired
    private TicketDTOMapper ticketDTOMapper;
    @Autowired
    private AirPlaneEntityDtoMapper airPlaneEntityDtoMapper;


    @Override
    public List<AirPlaneEntityDTO> getAllAirPlanes() {

        List<AirPlaneEntity> airPlaneEntities = airPlaneRepository.findAll();

        return airPlaneEntities.stream().map(airPlaneEntityDtoMapper::toAirPlaneEntityDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AirPlaneEntityDTO getAirPlaneById(Long id) {

        Optional<AirPlaneEntity> airPlaneEntity = airPlaneRepository.findById(id);
        return airPlaneEntity.map(airPlaneEntityDtoMapper::toAirPlaneEntityDTO).orElse(null);
    }

    @Override
    @Transactional
    public AirPlaneEntityDTO createAirPlane(AirPlaneEntityDTO airPlane) {

        AirPlaneEntity airPlaneEntity = airPlaneEntityDtoMapper.toAirPlaneEntity(airPlane);
        airPlaneEntity.setName(airPlane.getName());
        return airPlaneEntityDtoMapper.toAirPlaneEntityDTO(airPlaneRepository.save(airPlaneEntity));

    }

    @Override
    @Transactional
    public AirPlaneEntityDTO updateAirPlane(Long id, AirPlaneEntityDTO airPlane) {

        AirPlaneEntity airPlaneEntity = airPlaneRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        airPlaneEntity.setName(airPlaneEntity.getName());

        return airPlaneEntityDtoMapper.toAirPlaneEntityDTO(airPlaneRepository.save(airPlaneEntity));

    }

    @Override
    public List<TicketEntityDTO> findAllTicketsByAirPlaneId(Long id) {
        AirPlaneEntity airPlaneEntity = airPlaneRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Not found"));

        return airPlaneEntity.getTickets().stream().map(ticketDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AirPlaneEntityDTO> searchAirPlane(String name) {

        List<AirPlaneEntity> airPlaneEntities = airPlaneRepository.searchTicket(name);

        return airPlaneEntities.stream().map(airPlaneEntityDtoMapper::toAirPlaneEntityDTO)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAirPlane(Long id) {

        AirPlaneEntity airPlaneEntityDTO = airPlaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));

        airPlaneRepository.delete(airPlaneEntityDTO);

    }
}

package com.air.airstore.Service.EntitiesService.ServiceImpl;

import com.air.airstore.EntityDTO.AirPlaneEntityDTO;
import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Exceptions.AirPlaneNotFoundException;
import com.air.airstore.Exceptions.TicketNotFoundException;
import com.air.airstore.Repository.AirPlaneRepository;
import com.air.airstore.Service.DTOMapper.ServiceDTO.AirPlaneEntityDtoMapper;
import com.air.airstore.Service.DTOMapper.ServiceDTO.TicketDTOMapper;
import com.air.airstore.Service.EntitiesService.AirPlaneService;
import com.air.airstore.model.AirPlaneEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirPlaneServiceImpl implements AirPlaneService {


    private final AirPlaneRepository airPlaneRepository;
    private static final Logger logger = LoggerFactory.getLogger(AirPlaneServiceImpl.class);

    private final TicketDTOMapper ticketDTOMapper;

    private final AirPlaneEntityDtoMapper airPlaneEntityDtoMapper;

    @Autowired
    public AirPlaneServiceImpl(AirPlaneRepository airPlaneRepository, TicketDTOMapper ticketDTOMapper, AirPlaneEntityDtoMapper airPlaneEntityDtoMapper) {
        this.airPlaneRepository = airPlaneRepository;
        this.ticketDTOMapper = ticketDTOMapper;
        this.airPlaneEntityDtoMapper = airPlaneEntityDtoMapper;
    }

    @Override
    public List<AirPlaneEntityDTO> getAllAirPlanes() {
        List<AirPlaneEntity> airPlaneEntities = airPlaneRepository.findAll();
        if (airPlaneEntities.isEmpty()) {
            logger.error("No AirPlane found");
            throw new AirPlaneNotFoundException("No AirPlane found");
        }
        logger.info("Air planes found");
        return airPlaneEntities.stream()
                .map(airPlaneEntityDtoMapper::toAirPlaneEntityDTO)
                .collect(Collectors.toList());


    }

    @Override
    public AirPlaneEntityDTO getAirPlaneById(Long id) {
        return airPlaneRepository.findById(id)
                .map(airPlaneEntityDtoMapper::toAirPlaneEntityDTO)
                .orElseThrow(() -> {
                    logger.error("No AirPlane found");
                    throw new AirPlaneNotFoundException("No AirPlane found");
                });
    }

    @Override
    @Transactional
    public AirPlaneEntityDTO createAirPlane(AirPlaneEntityDTO airPlane) {

        if (airPlaneRepository.existsByName(airPlane.getName())) {
            throw new IllegalArgumentException("AirPlane with name " + airPlane.getName() + " already exists");
        }

        AirPlaneEntity airPlaneEntity = airPlaneEntityDtoMapper.toAirPlaneEntity(airPlane);
        airPlaneEntity.setName(airPlane.getName());
        return airPlaneEntityDtoMapper.toAirPlaneEntityDTO(airPlaneRepository.save(airPlaneEntity));

    }

    @Override
    @Transactional
    public AirPlaneEntityDTO updateAirPlane(Long id, AirPlaneEntityDTO airPlane) {
        AirPlaneEntity airPlaneEntity = airPlaneRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        airPlaneEntity.setName(airPlaneEntity.getName());
        logger.info("AirPlane with ID {} created successfully", airPlaneEntity.getId());
        return airPlaneEntityDtoMapper.toAirPlaneEntityDTO(airPlaneRepository.save(airPlaneEntity));
    }

    @Override
    public List<TicketEntityDTO> findAllTicketsByAirPlaneId(Long id) {
        AirPlaneEntity airPlaneEntity = airPlaneRepository.findById(id).orElseThrow(()
                -> {
            logger.error("No AirPlane found");
            throw new AirPlaneNotFoundException("No AirPlane found");
        });
        return airPlaneEntity.getTickets().stream().map(ticketDTOMapper::toDTO)
                .collect(Collectors.toList());
    }


    public List<AirPlaneEntityDTO> searchAirPlane(String name, Double price, Integer seatNumber) {
        List<AirPlaneEntity> airPlaneEntities = new ArrayList<>();
        try {


            if (name != null && !name.isEmpty()) {
                logger.info("Searching AirPlane with name " + name);
                airPlaneEntities.addAll(airPlaneRepository.findByName(name));
            }
            if (price != null) {
                logger.info("Searching AirPlane with price " + price);
                airPlaneEntities.addAll(airPlaneRepository.findByPriceLess(price));
            }

            if (seatNumber != null) {
                logger.info("Searching AirPlane with seat " + seatNumber);
                airPlaneEntities.addAll(airPlaneRepository.findBySeatCount(seatNumber));
                airPlaneEntities = airPlaneEntities.stream().distinct().collect(Collectors.toList());
            }
        } catch (TicketNotFoundException e) {
            logger.error("Ticket not found");
            throw new TicketNotFoundException("Ticket not found");
        }
        return airPlaneEntities.stream()
                .map(airPlaneEntityDtoMapper::toAirPlaneEntityDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAirPlane(Long id) {
        AirPlaneEntity airPlaneEntityDTO = airPlaneRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("No AirPlane found");
                    throw new AirPlaneNotFoundException("No AirPlane found");
                });
        airPlaneRepository.delete(airPlaneEntityDTO);

    }
}

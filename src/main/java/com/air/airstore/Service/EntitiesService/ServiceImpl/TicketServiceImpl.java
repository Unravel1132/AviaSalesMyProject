package com.air.airstore.Service.EntitiesService.ServiceImpl;

import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Exceptions.AirPlaneNotFoundException;
import com.air.airstore.Exceptions.TicketNotFoundException;
import com.air.airstore.Repository.AirPlaneRepository;
import com.air.airstore.Repository.TicketEntityRepository;
import com.air.airstore.Service.DTOMapper.ServiceDTO.AirPlaneEntityDtoMapper;
import com.air.airstore.Service.DTOMapper.ServiceDTO.TicketDTOMapper;
import com.air.airstore.Service.EntitiesService.TicketService;
import com.air.airstore.model.AirPlaneEntity;
import com.air.airstore.model.TicketEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);


    private final TicketEntityRepository repository;

    private final TicketDTOMapper ticketDTOMapper;

    private final AirPlaneRepository airPlaneRepository;

    private final AirPlaneEntityDtoMapper airPlaneEntityDtoMapper;

    @Autowired
    public TicketServiceImpl(TicketEntityRepository repository, TicketDTOMapper ticketDTOMapper, AirPlaneRepository airPlaneRepository, AirPlaneEntityDtoMapper airPlaneEntityDtoMapper) {
        this.repository = repository;
        this.ticketDTOMapper = ticketDTOMapper;
        this.airPlaneRepository = airPlaneRepository;
        this.airPlaneEntityDtoMapper = airPlaneEntityDtoMapper;
    }

    @Override
    public List<TicketEntityDTO> getTickets() {
        try {
            List<TicketEntity> ticketEntities = repository.findAll();
            logger.info("Retrieved {} tickets from the database", ticketEntities.size());
            return ticketEntities.stream().map(ticketDTOMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error retrieving tickets: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public TicketEntityDTO getTicketById(Long id) {
        try {
            Optional<TicketEntity> ticketEntity = repository.findById(id);
            if (ticketEntity.isPresent()) {
                TicketEntity ticket = ticketEntity.get();
                return ticketDTOMapper.toDTO(ticket);
            } else {
                throw new TicketNotFoundException("Ticket with ID " + id + " not found");
            }
        } catch (TicketNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error retrieving ticket with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }


    @Override
    @Transactional
    public TicketEntityDTO createTicket(TicketEntityDTO ticket, Long id) {
        logger.info("Creating ticket for AirPlane with ID: {}", id);
        AirPlaneEntity airPlaneEntity = airPlaneRepository.findById(id).
                orElseThrow(() -> {
                    logger.info("AirPlane with ID {} not found", id);
                    return new AirPlaneNotFoundException("AirPlane with ID " + id + " not found");
                });
        TicketEntity ticketEntity = new TicketEntity(ticket.getPrice(), ticket.getTitle(), ticket.getSeatNumber());
        if (ticket.getAirPlaneEntity() != null) {
            AirPlaneEntity airPlaneEntity2 = airPlaneEntityDtoMapper.toAirPlaneEntity(ticket.getAirPlaneEntity());
            ticketEntity.setAirPlaneEntity(airPlaneEntity2);
        } else {
            ticketEntity.setAirPlaneEntity(airPlaneEntity);
        }
        TicketEntity savedTicketEntity = repository.save(ticketEntity);
        logger.info("Билет успешно создан для самолета с ID: {}", id);
        return ticketDTOMapper.toDTO(savedTicketEntity);
    }


    @Override
    @Transactional
    public TicketEntityDTO updateTicket(Long id, TicketEntityDTO ticket) {
        TicketEntity ticketEntity = repository.findById(id).orElseThrow(
                () -> {
                    logger.info("Ticket with ID {} not found", id);
                    return new TicketNotFoundException("Ticket with ID " + id + " not found");
                }
        );
        ticketEntity.setPrice(ticket.getPrice());
        ticketEntity.setTitle(ticket.getTitle());
        ticketEntity.setSeatNumber(ticket.getSeatNumber());

        if (ticket.getAirPlaneEntity() != null) {
            AirPlaneEntity airPlaneEntity = airPlaneEntityDtoMapper.toAirPlaneEntity(ticket.getAirPlaneEntity());
            ticketEntity.setAirPlaneEntity(airPlaneEntity);
        } else {
            ticketEntity.setAirPlaneEntity(null);
        }
        TicketEntity savedTicketEntity = repository.save(ticketEntity);
        logger.info("Ticket with ID: {} successfully updated", id);
        return ticketDTOMapper.toDTO(savedTicketEntity);
    }


    @Override
    public void deleteTicket(Long id) {
        logger.info("Deleting ticket with ID: {}", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new TicketNotFoundException("Ticket with ID " + id + " not found");
        }

    }


}

package com.air.airstore.Service.EntitiesService.ServiceImpl;

import com.air.airstore.EntityDTO.AirPlaneEntityDTO;
import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Exeptions.AirPlaneNotFoundException;
import com.air.airstore.Exeptions.TicketNotFoundException;
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

import java.util.Collections;
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
        try {
            Optional<AirPlaneEntity> airPlaneEntityOptional = airPlaneRepository.findById(id);
            if (airPlaneEntityOptional.isPresent()) {
                AirPlaneEntity airPlaneEntity = airPlaneEntityOptional.get();
                TicketEntity ticketEntity = new TicketEntity();
                ticketEntity.setPrice(ticket.getPrice());
                ticketEntity.setTitle(ticket.getTitle());
                ticketEntity.setSeatNumber(ticket.getSeatNumber());
                //Сначала я проверяю если не равно null
                if (ticket.getAirPlaneEntity() != null) {
                    AirPlaneEntity airPlaneEntity_2 = airPlaneEntityDtoMapper.toAirPlaneEntity(ticket.getAirPlaneEntity());
                    ticketEntity.setAirPlaneEntity(airPlaneEntity_2);
                }
                TicketEntity savedTicketEntity = repository.save(ticketEntity);
                return ticketDTOMapper.toDTO(savedTicketEntity);
            } else {
                throw new AirPlaneNotFoundException("AirPlane with ID " + id + " not found");
            }
        } catch (AirPlaneNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error creating ticket: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public TicketEntityDTO updateTicket(Long id, TicketEntityDTO ticket) {
    try {
        Optional<TicketEntity> ticketEntityOptional = repository.findById(id);
        if(ticketEntityOptional.isPresent()){
            TicketEntity ticketEntity = ticketEntityOptional.get();
            ticketEntity.setPrice(ticket.getPrice());
            ticketEntity.setTitle(ticket.getTitle());
            ticketEntity.setSeatNumber(ticket.getSeatNumber());

            if(ticket.getAirPlaneEntity() != null){
                AirPlaneEntity airPlaneEntity = airPlaneEntityDtoMapper.toAirPlaneEntity(ticket.getAirPlaneEntity());
                ticketEntity.setAirPlaneEntity(airPlaneEntity);
            }
            TicketEntity savedEntity = repository.save(ticketEntity);
            return ticketDTOMapper.toDTO(ticketEntity);
        }else{
            throw new TicketNotFoundException("Ticket with ID " + id + " not found");
        }
    }catch (TicketNotFoundException e){
        throw e;
    }catch (Exception e){
        logger.error("Error updating ticket: {}", e.getMessage(), e);
        throw e;
    }

    }


    @Override
    public void deleteTicket(Long id) {
        repository.deleteById(id);

    }


}

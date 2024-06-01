package com.air.airstore.Service.EntitiesService.ServiceImpl;

import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Repository.AirPlaneRepository;
import com.air.airstore.Repository.TicketEntityRepository;
import com.air.airstore.Service.DTOMapper.ServiceDTO.TicketDTOMapper;
import com.air.airstore.Service.EntitiesService.TicketService;
import com.air.airstore.model.AirPlaneEntity;
import com.air.airstore.model.TicketEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {


     private final TicketEntityRepository repository;

     private final TicketDTOMapper ticketDTOMapper;

     private final AirPlaneRepository airPlaneRepository;

     @Autowired
    public TicketServiceImpl(TicketEntityRepository repository, TicketDTOMapper ticketDTOMapper, AirPlaneRepository airPlaneRepository) {
        this.repository = repository;
        this.ticketDTOMapper = ticketDTOMapper;
        this.airPlaneRepository = airPlaneRepository;
    }

    @Override
    public List<TicketEntityDTO> getTickets() {

        List<TicketEntity> ticketEntities = repository.findAll();
        return ticketEntities.stream().map(ticketDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TicketEntityDTO getTicketById(Long id) {

        Optional<TicketEntity> ticketEntity = repository.findById(id);
        return ticketEntity.map(ticketDTOMapper::toDTO).orElse(null);

    }

    @Override
    @Transactional
    public TicketEntityDTO createTicket(TicketEntityDTO ticket, Long id) {

        AirPlaneEntity airPlaneEntity = airPlaneRepository.findById(id).orElseThrow(() -> new RuntimeException("Air plane not found"));

        TicketEntity ticketEntity = ticketDTOMapper.toEntity(ticket);
        ticketEntity.setAirPlaneEntity(airPlaneEntity);

        return ticketDTOMapper.toDTO(repository.save(ticketEntity));
    }

    @Override
    public TicketEntityDTO updateTicket(Long id, TicketEntityDTO ticket) {

        TicketEntity ticketEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticketEntity.setPrice(ticket.getPrice());
        ticketEntity.setSeatNumber(ticket.getSeatNumber());
        ticketEntity.setAirPlaneEntity(ticketEntity.getAirPlaneEntity());


        return ticketDTOMapper.toDTO(repository.save(ticketEntity));
    }

    @Override
    public List<TicketEntityDTO> findAllTicketsByAirplaneId(Long id) {

        Optional<AirPlaneEntity> airPlaneEntityOptional = airPlaneRepository.findById(id);
        AirPlaneEntity airPlaneEntity = airPlaneEntityOptional.orElse(null);

        if (airPlaneEntity == null) {
            return Collections.emptyList();
        }

        return airPlaneEntity.getTickets().stream().map(ticketDTOMapper::toDTO).collect(Collectors.toList());
    }



    @Override
    public void deleteTicket(Long id) {
        repository.deleteById(id);

    }



}

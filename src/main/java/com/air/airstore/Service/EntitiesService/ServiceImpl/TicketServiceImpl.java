package com.air.airstore.Service.EntitiesService.ServiceImpl;

import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Repository.AirPlaneRepository;
import com.air.airstore.Repository.TicketEntityRepository;
import com.air.airstore.Service.DTOMapper.ServiceDTO.TicketDTOMapper;
import com.air.airstore.Service.EntitiesService.OrderService;
import com.air.airstore.Service.EntitiesService.TicketService;
import com.air.airstore.model.AirPlaneEntity;
import com.air.airstore.model.TicketEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {


    @Autowired
    private TicketEntityRepository repository;

    @Autowired
    private TicketDTOMapper ticketDTOMapper;

    @Autowired
    private AirPlaneRepository airPlaneRepository;


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

        AirPlaneEntity airPlaneEntity = airPlaneRepository.findById(id).orElse(null);

        return airPlaneEntity.getTickets().stream().map(ticketDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteTicket(Long id) {

        AirPlaneEntity airPlaneEntity = airPlaneRepository.findById(id).orElse(null);
        repository.deleteById(id);

    }
}

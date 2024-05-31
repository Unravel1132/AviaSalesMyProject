package com.air.airstore.Service.EntitiesService;

import com.air.airstore.EntityDTO.TicketEntityDTO;

import java.util.List;

public interface TicketService {


    List<TicketEntityDTO> getTickets();
    TicketEntityDTO getTicketById(Long id);
    TicketEntityDTO createTicket(TicketEntityDTO ticket, Long id);
    TicketEntityDTO updateTicket(Long id, TicketEntityDTO ticket);
    List<TicketEntityDTO> findAllTicketsByAirplaneId(Long id);
    void deleteTicket(Long id);


}

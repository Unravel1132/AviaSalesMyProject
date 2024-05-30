package com.air.airstore.Service.EntitiesService;

import com.air.airstore.EntityDTO.TicketEntityDTO;

import java.util.List;

public interface TicketService {


    List<TicketEntityDTO> getTickets();
    TicketEntityDTO getTicketById(Long id);
    TicketEntityDTO createTicket(TicketEntityDTO ticket);
    TicketEntityDTO updateTicket(Long id, TicketEntityDTO ticket);
    void deleteTicket(Long id);


}

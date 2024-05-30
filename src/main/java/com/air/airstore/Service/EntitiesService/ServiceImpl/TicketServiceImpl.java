package com.air.airstore.Service.EntitiesService.ServiceImpl;

import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Service.EntitiesService.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    @Override
    public List<TicketEntityDTO> getTickets() {
        return List.of();
    }

    @Override
    public TicketEntityDTO getTicketById(Long id) {
        return null;
    }

    @Override
    public TicketEntityDTO createTicket(TicketEntityDTO ticket) {
        return null;
    }

    @Override
    public TicketEntityDTO updateTicket(Long id, TicketEntityDTO ticket) {
        return null;
    }

    @Override
    public void deleteTicket(Long id) {

    }
}

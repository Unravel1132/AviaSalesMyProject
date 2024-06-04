package com.air.airstore.Service.DTOMapper;

import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Service.DTOMapper.ServiceDTO.TicketDTOMapper;
import com.air.airstore.model.TicketEntity;
import org.springframework.stereotype.Service;

@Service
public class TicketDTOMapperImpl implements TicketDTOMapper {

    @Override
    public TicketEntity toEntity(TicketEntityDTO ticketEntityDTO) {

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(ticketEntityDTO.getId());
        ticketEntity.setTitle(ticketEntityDTO.getTitle());
        ticketEntity.setPrice(ticketEntityDTO.getPrice());
        ticketEntity.setSeatNumber(ticketEntityDTO.getSeatNumber());

        return ticketEntity;
    }

    @Override
    public TicketEntityDTO toDTO(TicketEntity ticketEntity) {

        TicketEntityDTO ticketEntityDTO = new TicketEntityDTO();
        ticketEntityDTO.setId(ticketEntity.getId());
        ticketEntityDTO.setTitle(ticketEntity.getTitle());
        ticketEntityDTO.setSeatNumber(ticketEntity.getSeatNumber());
        ticketEntityDTO.setPrice(ticketEntity.getPrice());

        return ticketEntityDTO;
    }
}

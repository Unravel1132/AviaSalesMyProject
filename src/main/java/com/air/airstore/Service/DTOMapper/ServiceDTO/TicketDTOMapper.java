package com.air.airstore.Service.DTOMapper.ServiceDTO;

import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.model.TicketEntity;

public interface TicketDTOMapper {

    TicketEntity toEntity(TicketEntityDTO ticketEntityDTO);
    TicketEntityDTO toDTO(TicketEntity ticketEntity);

}

package com.air.airstore.Service.DTOMapper.ServiceDTO;

import com.air.airstore.EntityDTO.OrderDTO;
import com.air.airstore.model.Order;

public interface OrderDtoMapper {

    OrderDTO toDto(Order orderDto);
    Order toEntity(OrderDTO order);


}

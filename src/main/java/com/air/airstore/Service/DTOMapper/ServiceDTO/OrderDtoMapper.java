package com.air.airstore.Service.DTOMapper.ServiceDTO;

import com.air.airstore.EntityDTO.OrderDTO;
import com.air.airstore.model.Order;

public interface OrderDtoMapper {

    Order toDto(OrderDTO orderDto);
    OrderDTO toEntity(Order order);


}

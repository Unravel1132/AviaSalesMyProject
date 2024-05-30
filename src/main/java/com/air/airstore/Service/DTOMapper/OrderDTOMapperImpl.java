package com.air.airstore.Service.DTOMapper;

import com.air.airstore.EntityDTO.OrderDTO;
import com.air.airstore.Service.DTOMapper.ServiceDTO.OrderDtoMapper;
import com.air.airstore.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderDTOMapperImpl implements OrderDtoMapper {

    @Override
    public Order toDto(OrderDTO orderDto) {

        Order order = new Order();
        order.setId(orderDto.getId());

        return order;

    }

    @Override
    public OrderDTO toEntity(Order order) {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        return orderDTO;

    }
}

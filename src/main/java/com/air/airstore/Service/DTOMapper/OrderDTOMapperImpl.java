package com.air.airstore.Service.DTOMapper;

import com.air.airstore.EntityDTO.OrderDTO;
import com.air.airstore.Service.DTOMapper.ServiceDTO.OrderDtoMapper;
import com.air.airstore.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderDTOMapperImpl implements OrderDtoMapper {

    @Override
    public OrderDTO toDto(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCreatedDate(order.getCreatedDate());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setPaymentInfo(order.getPaymentInfo());
        return orderDTO;
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCreatedDate(orderDTO.getCreatedDate());
        order.setStatus(orderDTO.getStatus());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setPaymentInfo(orderDTO.getPaymentInfo());
        return order;
    }
}

package com.air.airstore.Service.EntitiesService.ServiceImpl;

import com.air.airstore.EntityDTO.OrderDTO;
import com.air.airstore.Repository.OrderRepository;
import com.air.airstore.Service.DTOMapper.ServiceDTO.OrderDtoMapper;
import com.air.airstore.Service.EntitiesService.OrderService;
import com.air.airstore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDtoMapper orderDtoMapper;

    @Override
    public List<OrderDTO> orderList() {
        List<Order> orderList =  orderRepository.findAll();
        return orderList.stream().map(orderDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}

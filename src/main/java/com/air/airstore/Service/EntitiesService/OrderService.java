package com.air.airstore.Service.EntitiesService;

import com.air.airstore.EntityDTO.OrderDTO;
import com.air.airstore.model.EnumModel.OrderStatus;

import java.util.List;

public interface OrderService {

    List<OrderDTO> orderList();
    void updateOrderStatus(Long orderId, OrderStatus newStatus);

}

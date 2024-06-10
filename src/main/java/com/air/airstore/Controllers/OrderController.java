package com.air.airstore.Controllers;


import com.air.airstore.EntityDTO.OrderDTO;
import com.air.airstore.Service.EntitiesService.OrderService;
import com.air.airstore.Service.EntitiesService.ServiceImpl.OrderServiceImpl;
import com.air.airstore.model.EnumModel.OrderStatus;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v4")
public class OrderController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDTO>> getAllOrder(){
        List<OrderDTO> orderDTOS = orderService.orderList();
        return ResponseEntity.ok(orderDTOS);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable("orderId") Long orderId,
                                                      @RequestBody OrderStatus newStatus){
        orderService.updateOrderStatus(orderId, newStatus);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
package com.air.airstore.Controllers;


import com.air.airstore.EntityDTO.OrderDTO;
import com.air.airstore.Service.EntitiesService.OrderService;
import com.air.airstore.Service.EntitiesService.ServiceImpl.OrderServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

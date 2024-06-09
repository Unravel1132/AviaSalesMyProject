package com.air.airstore.EntityDTO;

import com.air.airstore.model.EnumModel.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;
    private List<TicketEntityDTO> tickets;
    private LocalDateTime createdDate;
    private OrderStatus status;
    private BigDecimal totalPrice;
    private String paymentInfo;
}

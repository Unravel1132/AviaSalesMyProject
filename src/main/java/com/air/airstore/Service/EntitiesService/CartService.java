package com.air.airstore.Service.EntitiesService;

import com.air.airstore.EntityDTO.CartDTO;

public interface CartService {
    CartDTO getCart(Long userId);
    CartDTO addTicketToCart(Long userId, Long ticketId);
    void clearCart(Long userId);
}
package com.air.airstore.Service.EntitiesService.ServiceImpl;

import com.air.airstore.EntityDTO.CartDTO;
import com.air.airstore.Repository.CartRepository;
import com.air.airstore.Repository.TicketEntityRepository;
import com.air.airstore.Service.DTOMapper.ServiceDTO.CartDTOMapper;
import com.air.airstore.Service.EntitiesService.CartService;
import com.air.airstore.model.Cart;
import com.air.airstore.model.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TicketEntityRepository ticketRepository;

    @Autowired
    private CartDTOMapper cartDTOMapper;

    @Override
    public CartDTO getCart(Long userId) {
        Optional<Cart> optionalCart = cartRepository.findById(userId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            return cartDTOMapper.toCartDTO(cart);
        } else {
            throw new RuntimeException("Корзина пользователя с ID " + userId + " не найдена");
        }
    }

    @Override
    public CartDTO addTicketToCart(Long userId, Long ticketId) {
        Optional<Cart> optionalCart = cartRepository.findById(userId);
        if (optionalCart.isPresent()) {
            TicketEntity ticketEntity = ticketRepository.findById(ticketId).orElse(null);
            if (ticketEntity != null) {
                Cart cart = optionalCart.get();
                cart.getTicketsEntity().add(ticketEntity);
                cartRepository.save(cart);
                return cartDTOMapper.toCartDTO(cart);
            } else {
                throw new RuntimeException("Билет с ID " + ticketId + " не найден");
            }
        } else{
            throw new RuntimeException("Корзина пользователя с ID " + userId + " не найдена");
        }

    }

    @Override
    public void clearCart(Long userId) {
        Optional<Cart> cartOptional = cartRepository.findById(userId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.getTicketsEntity().clear();
            cartRepository.save(cart);
        }
    }
}

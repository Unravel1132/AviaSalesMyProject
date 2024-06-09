package com.air.airstore.Service.DTOMapper;

import com.air.airstore.EntityDTO.CartDTO;
import com.air.airstore.Service.DTOMapper.ServiceDTO.CartDTOMapper;
import com.air.airstore.model.Cart;
import org.springframework.stereotype.Service;

@Service
public class CartDTOMapperImpl implements CartDTOMapper {
    @Override
    public Cart toCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setUser(cartDTO.getUser());
        return cart;
    }

    @Override
    public CartDTO toCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUser(cart.getUser());
        return cartDTO;
    }
}

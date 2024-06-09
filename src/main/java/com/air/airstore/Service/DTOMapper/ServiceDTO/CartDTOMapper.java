package com.air.airstore.Service.DTOMapper.ServiceDTO;

import com.air.airstore.EntityDTO.CartDTO;
import com.air.airstore.model.Cart;

public interface CartDTOMapper {

    Cart toCart(CartDTO cartDTO);
    CartDTO toCartDTO(Cart cart);

}

package com.air.airstore.Controllers;


import com.air.airstore.EntityDTO.CartDTO;
import com.air.airstore.Service.EntitiesService.ServiceImpl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v3")
public class CartController {

    private final CartServiceImpl cartService;

    @Autowired
    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long id) {
        CartDTO cartDTO = cartService.getCart(id);
        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addCart(@RequestParam Long userId, @RequestParam Long ticketId) {
        CartDTO cartDTO = cartService.addTicketToCart(userId, ticketId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CartDTO> deleteCart(@RequestParam Long userId) {
            cartService.clearCart(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

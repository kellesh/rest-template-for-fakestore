package dev.ellesh.fakestorerestclient.controllers;


import dev.ellesh.fakestorerestclient.models.Cart;
import dev.ellesh.fakestorerestclient.services.CartService;
import dev.ellesh.fakestorerestclient.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // GET all carts
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    // GET cart by ID
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable int id) throws NotFoundException {
        return ResponseEntity.ok(cartService.getCartById(id));
    }

    // POST create cart
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.createCart(cart));
    }

    // PUT update cart
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart cart)
            throws NotFoundException {
        cartService.updateCart(id, cart);
        return ResponseEntity.ok(cart);
    }

    // DELETE cart
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id) throws NotFoundException {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}

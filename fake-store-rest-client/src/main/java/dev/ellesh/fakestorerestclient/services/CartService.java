package dev.ellesh.fakestorerestclient.services;
import dev.ellesh.fakestorerestclient.models.Cart;
import dev.ellesh.fakestorerestclient.exceptions.NotFoundException;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();
    Cart getCartById(int id) throws NotFoundException;
    Cart createCart(Cart cart);
    void updateCart(int id, Cart cart) throws NotFoundException;
    void deleteCart(int id) throws NotFoundException;
}

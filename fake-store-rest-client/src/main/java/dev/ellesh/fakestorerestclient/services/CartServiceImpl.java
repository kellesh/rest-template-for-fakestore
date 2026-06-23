package dev.ellesh.fakestorerestclient.services;


import dev.ellesh.fakestorerestclient.exceptions.NotFoundException;
import dev.ellesh.fakestorerestclient.models.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://fakestoreapi.com/carts";

    public CartServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Cart> getAllCarts() {
        ResponseEntity<Cart[]> response =
                restTemplate.getForEntity(BASE_URL, Cart[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Cart getCartById(int id) throws NotFoundException {
        Cart cart = restTemplate.getForObject(BASE_URL + "/" + id, Cart.class);
        if (cart == null) {
            throw new NotFoundException("Cart not found with id " + id);
        }
        return cart;
    }

    @Override
    public Cart createCart(Cart cart) {
        return restTemplate.postForObject(BASE_URL, cart, Cart.class);
    }

    @Override
    public void updateCart(int id, Cart cart) throws NotFoundException {
        getCartById(id); // throws if not found
        restTemplate.put(BASE_URL + "/" + id, cart);
    }

    @Override
    public void deleteCart(int id) throws NotFoundException {
        getCartById(id); // throws if not found
        restTemplate.delete(BASE_URL + "/" + id);
    }
}

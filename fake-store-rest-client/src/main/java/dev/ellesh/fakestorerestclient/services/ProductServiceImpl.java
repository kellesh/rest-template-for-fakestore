package dev.ellesh.fakestorerestclient.services;


import dev.ellesh.fakestorerestclient.exceptions.NotFoundException;
import dev.ellesh.fakestorerestclient.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://fakestoreapi.com/products";

    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<Product[]> response =
                restTemplate.getForEntity(BASE_URL, Product[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Product getProductById(int id) throws NotFoundException {
        Product product = restTemplate.getForObject(BASE_URL + "/" + id, Product.class);
        if (product == null) {
            throw new NotFoundException("Product not found with id " + id);
        }
        return product;
    }

    @Override
    public Product createProduct(Product product) {
        return restTemplate.postForObject(BASE_URL, product, Product.class);
    }

    @Override
    public void updateProduct(int id, Product product) throws NotFoundException {
        getProductById(id); // throws if not found
        restTemplate.put(BASE_URL + "/" + id, product);
    }

    @Override
    public void deleteProduct(int id) throws NotFoundException {
        getProductById(id); // throws if not found
        restTemplate.delete(BASE_URL + "/" + id);
    }
}

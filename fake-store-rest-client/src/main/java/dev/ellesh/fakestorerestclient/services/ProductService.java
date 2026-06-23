package dev.ellesh.fakestorerestclient.services;

import dev.ellesh.fakestorerestclient.models.Product;
import dev.ellesh.fakestorerestclient.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(int id) throws NotFoundException;
    Product createProduct(Product product);
    void updateProduct(int id, Product product) throws NotFoundException;
    void deleteProduct(int id) throws NotFoundException;
}

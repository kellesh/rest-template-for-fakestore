package dev.ellesh.fakestorerestclient.services;

import dev.ellesh.fakestorerestclient.models.User;
import dev.ellesh.fakestorerestclient.exceptions.NotFoundException;
import dev.ellesh.fakestorerestclient.exceptions.BadRequestException;

import java.util.List;

public interface UserService {
    List<User> getAllUsers() throws BadRequestException;
    User getUserById(int id) throws NotFoundException;
    User createUser(User user) throws BadRequestException;
    void updateUser(int id, User user) throws NotFoundException, BadRequestException;
    void deleteUser(int id) throws NotFoundException;
}

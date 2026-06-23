package dev.ellesh.fakestorerestclient.services;

import dev.ellesh.fakestorerestclient.exceptions.NotFoundException;
import dev.ellesh.fakestorerestclient.exceptions.BadRequestException;
import dev.ellesh.fakestorerestclient.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://fakestoreapi.com/users";

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getAllUsers() throws BadRequestException {
        ResponseEntity<User[]> response =
                restTemplate.getForEntity(BASE_URL, User[].class);
        if (response.getBody() == null) {
            throw new BadRequestException("Failed to fetch users");
        }
        return Arrays.asList(response.getBody());
    }

    @Override
    public User getUserById(int id) throws NotFoundException {
        User user = restTemplate.getForObject(BASE_URL + "/" + id, User.class);
        if (user == null) {
            throw new NotFoundException("User not found with id " + id);
        }
        return user;
    }

    @Override
    public User createUser(User user) throws BadRequestException {
        User created = restTemplate.postForObject(BASE_URL, user, User.class);
        if (created == null) {
            throw new BadRequestException("Failed to create user");
        }
        return created;
    }

    @Override
    public void updateUser(int id, User user) throws NotFoundException, BadRequestException {
        User existing = getUserById(id);
        if (existing == null) {
            throw new NotFoundException("Cannot update. User not found with id " + id);
        }
        try {
            restTemplate.put(BASE_URL + "/" + id, user);
        } catch (Exception e) {
            throw new BadRequestException("Failed to update user with id " + id);
        }
    }

    @Override
    public void deleteUser(int id) throws NotFoundException {
        User existing = getUserById(id);
        if (existing == null) {
            throw new NotFoundException("Cannot delete. User not found with id " + id);
        }
        restTemplate.delete(BASE_URL + "/" + id);
    }
}

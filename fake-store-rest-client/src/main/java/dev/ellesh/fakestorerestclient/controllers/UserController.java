package dev.ellesh.fakestorerestclient.controllers;

import dev.ellesh.fakestorerestclient.models.User;
import dev.ellesh.fakestorerestclient.services.UserService;
import dev.ellesh.fakestorerestclient.exceptions.NotFoundException;
import dev.ellesh.fakestorerestclient.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() throws BadRequestException {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws NotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws BadRequestException {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user)
            throws NotFoundException, BadRequestException {
        userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) throws NotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

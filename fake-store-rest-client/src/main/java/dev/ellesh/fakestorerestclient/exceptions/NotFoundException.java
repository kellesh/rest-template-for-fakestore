package dev.ellesh.fakestorerestclient.exceptions;


// Custom exception for not found
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}


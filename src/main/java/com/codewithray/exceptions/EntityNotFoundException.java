package com.codewithray.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("Entity does not exist");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}

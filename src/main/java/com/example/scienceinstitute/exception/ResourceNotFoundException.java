package com.example.scienceinstitute.exception;

public class ResourceNotFoundException extends RuntimeException {

        public ResourceNotFoundException(String message) {
                super(message);
        }
}

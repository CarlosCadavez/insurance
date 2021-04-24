package br.com.csc.insurance.exception;

public class EntityExistsException extends RuntimeException {

    public EntityExistsException() {
    }

    public EntityExistsException(String message) {
        super(message);
    }
}

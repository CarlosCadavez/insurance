package br.com.csc.insurance.client.exception;

import br.com.csc.insurance.exception.EntityExistsException;

public class ClientAlreadyExistsException extends EntityExistsException {

    private static final String DEFAULT_MESSAGE = "Client already exists";

    public ClientAlreadyExistsException() {
        this(DEFAULT_MESSAGE);
    }

    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}

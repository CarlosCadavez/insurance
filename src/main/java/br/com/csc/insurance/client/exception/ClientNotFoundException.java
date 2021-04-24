package br.com.csc.insurance.client.exception;

import br.com.csc.insurance.exception.EntityNotFoundException;

public class ClientNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_MESSAGE = "Client not found";

    public ClientNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}

package br.com.csc.insurance.policy.exception;

import br.com.csc.insurance.exception.EntityNotFoundException;

public class PolicyNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_MESSAGE = "Policy not found";

    public PolicyNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public PolicyNotFoundException(String message) {
        super(message);
    }
}

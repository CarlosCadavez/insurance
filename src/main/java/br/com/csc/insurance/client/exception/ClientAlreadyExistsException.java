package br.com.csc.insurance.client.exception;

public class ClientAlreadyExistsException extends Exception {

    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}

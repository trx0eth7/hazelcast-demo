package ru.trx.hazelcastdemo.integration.exception;

/**
 * @author Alexander Vasiliev
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}

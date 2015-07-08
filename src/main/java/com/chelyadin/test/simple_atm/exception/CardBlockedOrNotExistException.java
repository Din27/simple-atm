package com.chelyadin.test.simple_atm.exception;

/**
 * @author Dmitriy Chelyadin
 *
 * Exception which is thrown when credit card is not found or blocked
 * Extends from RuntimeException, so that transactions can rollback at the point of exception.
 */
public class CardBlockedOrNotExistException extends RuntimeException {

    public CardBlockedOrNotExistException() {
        super();
    }

    public CardBlockedOrNotExistException(String message) {
        super(message);
    }

    public CardBlockedOrNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardBlockedOrNotExistException(Throwable cause) {
        super(cause);
    }
}

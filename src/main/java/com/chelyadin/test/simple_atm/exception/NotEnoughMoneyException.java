package com.chelyadin.test.simple_atm.exception;

/**
 * @author Dmitriy Chelyadin
 *
 * Exception that says that there is not enough money on account for making the operation (for example, withdrawal).
 * Extends from RuntimeException, so that transactions can rollback at the point of exception.
 */
public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException() {
        super();
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughMoneyException(Throwable cause) {
        super(cause);
    }
}

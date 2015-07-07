package com.chelyadin.test.simple_atm.exception;

/**
 * @author Dmitriy Chelyadin
 */
public class RulesConflictException extends RuntimeException {

    public RulesConflictException() {
        super();
    }

    public RulesConflictException(String message) {
        super(message);
    }

    public RulesConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public RulesConflictException(Throwable cause) {
        super(cause);
    }
}

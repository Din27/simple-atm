package com.chelyadin.test.simple_atm.exception;

/**
 * @author Dmitriy Chelyadin
 *
 * General withdraw rules breaking exception.
 * Extends from RuntimeException, so that transactions can rollback at the point of exception.
 */
public class WithdrawRulesConflictException extends RuntimeException {

    public WithdrawRulesConflictException() {
        super();
    }

    public WithdrawRulesConflictException(String message) {
        super(message);
    }

    public WithdrawRulesConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public WithdrawRulesConflictException(Throwable cause) {
        super(cause);
    }
}

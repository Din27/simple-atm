package com.chelyadin.test.simple_atm.exception;

/**
 * @author Dmitriy Chelyadin
 *
 * Exception that says that there is zero withdrawal amount entered for withdraw operation.
 * Extends from RuntimeException, so that transactions can rollback at the point of exception.
 */
public class WithdrawZeroAmountException extends WithdrawRulesConflictException {

    public WithdrawZeroAmountException() {
        super();
    }

    public WithdrawZeroAmountException(String message) {
        super(message);
    }

    public WithdrawZeroAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public WithdrawZeroAmountException(Throwable cause) {
        super(cause);
    }
}

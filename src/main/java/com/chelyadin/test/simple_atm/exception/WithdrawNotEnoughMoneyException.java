package com.chelyadin.test.simple_atm.exception;

/**
 * @author Dmitriy Chelyadin
 *
 * Exception that says that there is not enough money on account for withdrawing.
 * Extends from RuntimeException, so that transactions can rollback at the point of exception.
 */
public class WithdrawNotEnoughMoneyException extends WithdrawRulesConflictException {

    public WithdrawNotEnoughMoneyException() {
        super();
    }

    public WithdrawNotEnoughMoneyException(String message) {
        super(message);
    }

    public WithdrawNotEnoughMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public WithdrawNotEnoughMoneyException(Throwable cause) {
        super(cause);
    }
}

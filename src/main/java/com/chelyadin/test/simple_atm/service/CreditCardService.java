package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.CreditCard;

import java.math.BigDecimal;

/**
 * @author Dmitriy Chelyadin
 *
 * Service to work with Credit Cards and operations with them
 */
public interface CreditCardService {

    boolean checkCreditCard(String number);
    Integer incrementFailedLoginAttempts(String number);
    Integer resetFailedLoginAttempts(String number);
    CreditCard checkBalance(String number);
    CreditCard withdraw(String number, BigDecimal amount);
    Integer getLoginAttemptsLeft(String number);
}

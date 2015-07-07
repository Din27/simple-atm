package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.CreditCard;

import java.math.BigDecimal;

/**
 * @author Dmitriy Chelyadin
 */
public interface CreditCardService {

    // operations with credit card by number
    boolean checkCreditCard(String number);
    Integer incrementFailedLoginAttempts(String number);
    void resetFailedLoginAttempts(String number);

    // operations with currently logged in credit card
    CreditCard checkBalanceForCurrent();
    CreditCard withdrawForCurrent(BigDecimal amount);
}

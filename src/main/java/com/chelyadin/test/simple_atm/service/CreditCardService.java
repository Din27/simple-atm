package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.CreditCard;

import java.math.BigDecimal;

/**
 * @author Dmitriy Chelyadin
 */
public interface CreditCardService {

    boolean checkCreditCard(String number);

    CreditCard checkBalance();
    void withdraw(BigDecimal amount);
}

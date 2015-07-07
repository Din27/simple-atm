package com.chelyadin.test.simple_atm.service;

import java.math.BigDecimal;

/**
 * @author Dmitriy Chelyadin
 */
public interface OperationHistoryService {
    void saveBalanceOperation(String cardNumber);
    void saveWithdrawalOperation(String cardNumber, BigDecimal withdrawalAmount);
}

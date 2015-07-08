package com.chelyadin.test.simple_atm.service;

import java.math.BigDecimal;

/**
 * @author Dmitriy Chelyadin
 *
 * Service to work with Operations History
 */
public interface OperationHistoryService {
    void saveBalanceOperation(String cardNumber);
    void saveWithdrawalOperation(String cardNumber, BigDecimal withdrawalAmount);
}

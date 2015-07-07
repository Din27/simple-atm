package com.chelyadin.test.simple_atm.service;

/**
 * @author Dmitriy Chelyadin
 */
public interface OperationHistoryService {
    void saveBalanceOperation(String cardNumber);
}

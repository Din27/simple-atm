package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.repository.CreditCardRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Dmitriy Chelyadin
 *
 * Service to work with Credit Cards and operations with them
 */
@Service
public class CreditCardServiceImpl extends BaseService implements CreditCardService {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    private CreditCardRepo creditCardRepo;
    private OperationHistoryService operationHistoryService;

    @Autowired
    public CreditCardServiceImpl(CreditCardRepo creditCardRepo, OperationHistoryService operationHistoryService) {
        this.creditCardRepo = creditCardRepo;
        this.operationHistoryService = operationHistoryService;
    }


    /**
     * Checks if credit card exists and not blocked
     */
    @Override
    public boolean checkCreditCard(String number) {
        CreditCard creditCard = creditCardRepo.findOne(number);
        return creditCard != null && creditCard.isAccountNonLocked();
    }

    /**
     * Increments failed login attempts
     */
    @Override
    public Integer incrementFailedLoginAttempts(String number) {
        CreditCard creditCard = creditCardRepo.findOne(number);
        creditCard.incrementFailedLoginAttempts();
        CreditCard savedCreditCard = creditCardRepo.save(creditCard);
        return savedCreditCard.getFailedLoginAttempts();
    }

    /**
     * Resets failed login attempts
     */
    @Override
    public Integer resetFailedLoginAttempts(String number) {
        CreditCard creditCard = creditCardRepo.findOne(number);
        creditCard.resetFailedLoginAttempts();
        CreditCard savedCreditCard = creditCardRepo.save(creditCard);
        return savedCreditCard.getFailedLoginAttempts();
    }

    /**
     * Checks balance and saves the balance operation to the operations history table in one transaction
     */
    @Override
    public CreditCard checkBalance(String number) {
        CreditCard creditCard = creditCardRepo.findOne(number);
        operationHistoryService.saveBalanceOperation(creditCard.getNumber());
        return creditCard;
    }

    /**
     * Withdraws money and saves the withdraw operation to the operations history table in one transaction
     */
    @Override
    public CreditCard withdraw(String number, BigDecimal withdrawalAmount) {
        CreditCard creditCard = creditCardRepo.findOne(number);
        creditCard.withdraw(withdrawalAmount);
        CreditCard savedCreditCard = creditCardRepo.save(creditCard);
        operationHistoryService.saveWithdrawalOperation(savedCreditCard.getNumber(), withdrawalAmount);
        return savedCreditCard;
    }

    /**
     * Checks how many login attempts are there left before the card is blocked
     */
    @Override
    public Integer getLoginAttemptsLeft(String number) {
        CreditCard creditCard = creditCardRepo.findOne(number);
        return CreditCard.FAILED_LOGIN_ATTEMPTS_TO_BLOCK - creditCard.getFailedLoginAttempts();
    }

}

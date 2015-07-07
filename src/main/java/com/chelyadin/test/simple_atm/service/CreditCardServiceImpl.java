package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.repository.CreditCardRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Dmitriy Chelyadin
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

    @Override
    public boolean checkCreditCard(String number) {
        CreditCard creditCard = creditCardRepo.findOne(number);
        return creditCard != null;
    }

    @Override
    public CreditCard checkBalance() {
        CreditCard creditCard = getCurrentCreditCard();
        operationHistoryService.saveBalanceOperation(creditCard.getNumber());
        return creditCard;
    }

    @Override
    public CreditCard withdraw(BigDecimal withdrawalAmount) {
        CreditCard creditCard = getCurrentCreditCard();
        creditCard.withdraw(withdrawalAmount);
        CreditCard savedCreditCard = creditCardRepo.save(creditCard);
        operationHistoryService.saveWithdrawalOperation(savedCreditCard.getNumber(), withdrawalAmount);
        return savedCreditCard;

    }

    private CreditCard getCurrentCreditCard() {
        return (CreditCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

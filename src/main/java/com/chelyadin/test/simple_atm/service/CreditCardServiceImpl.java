package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.repository.CreditCardRepo;
import com.chelyadin.test.simple_atm.repository.OperationRepo;
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
    public void withdraw(CreditCard creditCard, BigDecimal amount) {
        //TODO withdraw, throw exception if something goes wrong
        //TODO save operation
        //TODO check transaction
    }
}

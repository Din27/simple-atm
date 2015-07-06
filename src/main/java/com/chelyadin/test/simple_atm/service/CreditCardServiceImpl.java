package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.repository.CreditCardRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Dmitriy Chelyadin
 */
@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);


    private CreditCardRepo creditCardRepo;

    @Autowired
    public CreditCardServiceImpl(CreditCardRepo creditCardRepo) {
        this.creditCardRepo = creditCardRepo;
    }

    public boolean checkCreditCard(String number) {
        CreditCard creditCard = creditCardRepo.findOne(number);
        boolean result = creditCard != null;
        logger.info(String.format("Credit Card %s is %s", number, result ? "found" : "not found"));
        return result;
    }
}

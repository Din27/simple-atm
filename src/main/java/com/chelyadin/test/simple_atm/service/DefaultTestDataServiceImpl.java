package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.repository.CreditCardRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Service for creating default data. Should not be used in production, just for the comfort
 *
 * @author Dmitriy Chelyadin
 */
@Service
public class DefaultTestDataServiceImpl extends BaseService implements DefaultTestDataService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTestDataServiceImpl.class);

    private CreditCardRepo creditCardRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultTestDataServiceImpl(CreditCardRepo creditCardRepo, PasswordEncoder passwordEncoder) {
        this.creditCardRepo = creditCardRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createDefaultTestDataIfNeeded() {
        if (creditCardRepo.count() == 0L) {
            logger.info("Application Ready: Creating default test data for credit cards");
            List<CreditCard> defaultTestCreditCards = Arrays.asList(
                    new CreditCard("1111-2222-3333-4444", passwordEncoder.encode("1111"), new BigDecimal("10000.00")),
                    new CreditCard("5555-6666-7777-8888", passwordEncoder.encode("5555"), new BigDecimal("10.00")),
                    new CreditCard("1234-5678-1234-5678", passwordEncoder.encode("1234"), new BigDecimal("2000.00")),
                    new CreditCard("1111-1111-1111-1111", passwordEncoder.encode("1111"), new BigDecimal("3500.00")));
            creditCardRepo.save(defaultTestCreditCards);
        } else {
            logger.info("Application Ready: Not creating default test data for credit cards, because there are already some in DB");
        }
    }

}

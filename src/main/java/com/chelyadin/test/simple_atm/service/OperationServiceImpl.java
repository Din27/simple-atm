package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.Operation;
import com.chelyadin.test.simple_atm.domain.OperationCode;
import com.chelyadin.test.simple_atm.repository.OperationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author Dmitriy Chelyadin
 */
@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

    private OperationRepo operationRepo;

    @Autowired
    public OperationServiceImpl(OperationRepo operationRepo) {
        this.operationRepo = operationRepo;
    }

    @Override
    public void saveBalanceOperation(String cardNumber) {
        logger.info("Saving balance operation");

        Operation operation = new Operation(cardNumber, new Date(), OperationCode.BALANCE);
        operationRepo.save(operation);
    }
}

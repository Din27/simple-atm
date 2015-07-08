package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.Operation;
import com.chelyadin.test.simple_atm.domain.OperationCode;
import com.chelyadin.test.simple_atm.repository.OperationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Dmitriy Chelyadin
 *
 * Service to work with Operations History
 */
@Service
public class OperationHistoryServiceImpl extends BaseService implements OperationHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(OperationHistoryServiceImpl.class);

    private OperationRepo operationRepo;

    @Autowired
    public OperationHistoryServiceImpl(OperationRepo operationRepo) {
        this.operationRepo = operationRepo;
    }

    /**
     * Saves balance operation data to operations history table
     */
    @Override
    public void saveBalanceOperation(String cardNumber) {
        logger.info("Saving balance operation");
        Operation operation = new Operation(cardNumber, new Date(), OperationCode.BALANCE);
        operationRepo.save(operation);
    }

    /**
     * Saves withdrawal operation data to operations history table
     */
    @Override
    public void saveWithdrawalOperation(String cardNumber, BigDecimal withdrawalAmount) {
        logger.info("Saving withdrawal operation");
        Operation operation = new Operation(cardNumber, new Date(), OperationCode.WITHDRAWAL, withdrawalAmount);
        operationRepo.save(operation);
    }
}

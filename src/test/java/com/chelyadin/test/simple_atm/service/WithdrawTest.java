package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.exception.WithdrawNotEnoughMoneyException;
import com.chelyadin.test.simple_atm.exception.WithdrawRulesConflictException;
import com.chelyadin.test.simple_atm.exception.WithdrawZeroAmountException;
import com.chelyadin.test.simple_atm.repository.CreditCardRepo;
import org.junit.Assert;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Dmitriy Chelyadin
 */
public class WithdrawTest {

    private static final String NUMBER = "1111-1111-1111-1111";
    private static final String FAKE_PIN = "ENCODED_PIN";
    private static final BigDecimal BALANCE_AMOUNT = new BigDecimal("1000");

    private Mockery mockery;

    private CreditCard creditCard;

    private CreditCardService creditCardService;

    private CreditCardRepo mockCreditCardRepo;
    private OperationHistoryService mockOperationHistoryService;

    @Before
    public void setUp() {
        mockery = new Mockery();
        mockCreditCardRepo = mockery.mock(CreditCardRepo.class);
        mockOperationHistoryService = mockery.mock(OperationHistoryService.class);
        creditCardService = new CreditCardServiceImpl(mockCreditCardRepo, mockOperationHistoryService);

        creditCard = new CreditCard(NUMBER, FAKE_PIN, BALANCE_AMOUNT);

        mockery.checking(new Expectations() {{
            oneOf(mockCreditCardRepo).findOne(NUMBER);
            will(returnValue(creditCard));
        }});
    }

    @Test
    public void testWithdrawSuccess_lessThanBalance() throws Exception {
        BigDecimal withdrawalAmount = new BigDecimal("100");

        mockery.checking(new Expectations() {{
            oneOf(mockCreditCardRepo).save(creditCard);
            will(returnValue(creditCard));
            oneOf(mockOperationHistoryService).saveWithdrawalOperation(NUMBER, withdrawalAmount);
        }});

        CreditCard actualCreditCard = creditCardService.withdraw(NUMBER, withdrawalAmount);
        Assert.assertNotNull(actualCreditCard);
        Assert.assertEquals(new BigDecimal("900"), actualCreditCard.getAmount());
    }

    @Test
    public void testWithdrawSuccess_equalToBalance() throws Exception {
        BigDecimal withdrawalAmount = BALANCE_AMOUNT;

        mockery.checking(new Expectations() {{
            oneOf(mockCreditCardRepo).save(creditCard);
            will(returnValue(creditCard));
            oneOf(mockOperationHistoryService).saveWithdrawalOperation(NUMBER, withdrawalAmount);
        }});

        CreditCard actualCreditCard = creditCardService.withdraw(NUMBER, withdrawalAmount);
        Assert.assertNotNull(actualCreditCard);
        Assert.assertEquals(new BigDecimal("0"), actualCreditCard.getAmount());
    }


    @Test(expected = WithdrawNotEnoughMoneyException.class)
    public void testWithdrawError_notEnoughMoney() throws Exception {
        BigDecimal withdrawalAmount = new BigDecimal("1001");

        mockery.checking(new Expectations() {{
            never(mockCreditCardRepo).save(creditCard);
            never(mockOperationHistoryService).saveWithdrawalOperation(NUMBER, withdrawalAmount);
        }});

        creditCardService.withdraw(NUMBER, withdrawalAmount);
    }

    @Test(expected = WithdrawZeroAmountException.class)
    public void testWithdrawError_zeroWithdrawalAmount() throws Exception {
        BigDecimal withdrawalAmount = new BigDecimal("0");

        mockery.checking(new Expectations() {{
            never(mockCreditCardRepo).save(creditCard);
            never(mockOperationHistoryService).saveWithdrawalOperation(NUMBER, withdrawalAmount);
        }});

        creditCardService.withdraw(NUMBER, withdrawalAmount);
    }


    @Test(expected = WithdrawRulesConflictException.class)
    public void testWithdrawError_withdrawalAmountIsNegative() throws Exception {
        BigDecimal withdrawalAmount = new BigDecimal("-1");

        mockery.checking(new Expectations() {{
            never(mockCreditCardRepo).save(creditCard);
            never(mockOperationHistoryService).saveWithdrawalOperation(NUMBER, withdrawalAmount);
        }});

        creditCardService.withdraw(NUMBER, withdrawalAmount);
    }

    @After
    public void tearDown() {
        mockery.assertIsSatisfied();
    }
}

package com.chelyadin.test.simple_atm.web;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.exception.WithdrawNotEnoughMoneyException;
import com.chelyadin.test.simple_atm.exception.WithdrawZeroAmountException;
import com.chelyadin.test.simple_atm.form.WithdrawalForm;
import com.chelyadin.test.simple_atm.service.CreditCardService;
import com.chelyadin.test.simple_atm.service.OperationHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dmitriy Chelyadin
 */
@Controller
public class OperationsController extends BaseSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(OperationsController.class);

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private OperationHistoryService operationHistoryService;

    @RequestMapping("/operations")
    public String operations() {
        logger.info("Operations page request");
        return "operations";
    }

    @RequestMapping("/balance")
    public ModelAndView balance() {
        logger.info("Balance operation request");

        String number = getCurrentCreditCardNumber();
        validateCard(number);

        CreditCard creditCard = creditCardService.checkBalance(number);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("number", creditCard.getNumber());
        modelAndView.addObject("amount", creditCard.getAmount());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        modelAndView.addObject("currentDate", dateFormat.format(new Date()));

        modelAndView.setViewName("balance");
        return modelAndView;
    }

    @RequestMapping("/withdrawal")
    public ModelAndView withdrawal(
            @RequestParam(value = "notEnoughMoney", required = false) String notEnoughMoney,
            @RequestParam(value = "emptyWithdrawalAmount", required = false) String emptyWithdrawalAmount) {
        logger.info("Withdrawal page request");

        String number = getCurrentCreditCardNumber();
        validateCard(number);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("number", number);
        if (emptyWithdrawalAmount != null) {
            modelAndView.addObject("error", "Withdrawal amount can not be empty");
        } else if (notEnoughMoney != null) {
            modelAndView.addObject("error", "Not enough money on credit card");
        }

        modelAndView.setViewName("withdrawal");
        return modelAndView;
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public ModelAndView withdraw(@ModelAttribute WithdrawalForm form) {
        logger.info(String.format("Withdraw operation request, withdrawal amount = %s$", form.getWithdrawalAmount()));

        String number = getCurrentCreditCardNumber();
        validateCard(number);

        // TODO back-end validation
        BigDecimal withdrawalAmount = new BigDecimal(form.getWithdrawalAmount());

        CreditCard savedCreditCard = creditCardService.withdraw(number, withdrawalAmount);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("number", savedCreditCard.getNumber());
        modelAndView.addObject("balanceAmount", savedCreditCard.getAmount());
        modelAndView.addObject("withdrawalAmount", withdrawalAmount);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        modelAndView.addObject("currentDate", dateFormat.format(new Date()));

        modelAndView.setViewName("report");
        return modelAndView;
    }

    /**
     * In case of empty withdrawal amount redirect back to withdrawal page and show error msg
     */
    @ExceptionHandler(WithdrawZeroAmountException.class)
    public String handleWithdrawZeroAmount(Exception ex) {
        return "redirect:/withdrawal?emptyWithdrawalAmount";
    }

    /**
     * In case of not enough money on card to withdraw redirect back to withdrawal page and show error msg
     */
    @ExceptionHandler(WithdrawNotEnoughMoneyException.class)
    public String handleWithdrawNotEnoughMoney(Exception ex) {
        return "redirect:/withdrawal?notEnoughMoney";
    }

}

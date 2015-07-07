package com.chelyadin.test.simple_atm.web;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.form.WithdrawalForm;
import com.chelyadin.test.simple_atm.service.CreditCardService;
import com.chelyadin.test.simple_atm.service.OperationHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dmitriy Chelyadin
 */
@Controller
public class OperationsController {

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

        CreditCard creditCard = creditCardService.checkBalance();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("number", creditCard.getNumber());
        modelAndView.addObject("amount", creditCard.getAmount());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        modelAndView.addObject("currentDate", dateFormat.format(new Date()));

        modelAndView.setViewName("balance");
        return modelAndView;
    }

    @RequestMapping("/withdrawal")
    public ModelAndView withdrawal() {
        logger.info("Withdrawal page request");

        CreditCard creditCard = (CreditCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("number", creditCard.getNumber());
        modelAndView.setViewName("withdrawal");
        return modelAndView;
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public ModelAndView withdraw(@ModelAttribute WithdrawalForm form) {
        logger.info(String.format("Withdraw operation request, withdrawal amount = %s$", form.getWithdrawalAmount()));

        creditCardService.withdraw(new BigDecimal(form.getWithdrawalAmount())); // TODO validate first

        // TODO add info to report page

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("report");
        return modelAndView;
    }
}

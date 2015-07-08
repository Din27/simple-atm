package com.chelyadin.test.simple_atm.web;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.exception.CardBlockedOrNotExistException;
import com.chelyadin.test.simple_atm.exception.WithdrawNotEnoughMoneyException;
import com.chelyadin.test.simple_atm.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Dmitriy Chelyadin
 */
@Controller
public abstract class BaseSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(BaseSecurityController.class);


    @Autowired
    private CreditCardService creditCardService;

    protected String getCurrentCreditCardNumber() {
        return ((CreditCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNumber();
    }

    protected void validateCard(String number) {
        if (!creditCardService.checkCreditCard(number)) {
            logger.info(String.format("Validate card: Card %s is blocked or does not exist", number));
            throw new CardBlockedOrNotExistException();
        }
    }

    @ExceptionHandler(CardBlockedOrNotExistException.class)
    public String handleCardBlockedOrNotExist(Exception ex) {
        return "redirect:/errorBlockedOrNotExist";
    }

}

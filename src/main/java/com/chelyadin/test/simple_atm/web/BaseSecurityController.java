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
 *
 * Has some useful functions for working with security
 */
@Controller
public abstract class BaseSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(BaseSecurityController.class);


    @Autowired
    private CreditCardService creditCardService;

    /**
     * Gets current logged in credit card number from Spring Security.
     * IMPORTANT! We should not get whole {@link CreditCard} object from
     * {@link SecurityContextHolder} because this could give us old (detached?) CreditCard object.
     */
    protected String getCurrentCreditCardNumber() {
        return ((CreditCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNumber();
    }

    /**
     * Validates credit card by number - checks if the card exist and if it's not blocked.
     * IMPORTANT! Should be run before each request!
     * @throws com.chelyadin.test.simple_atm.exception.CardBlockedOrNotExistException
     */
    protected void validateCard(String number) {
        if (!creditCardService.checkCreditCard(number)) {
            logger.info(String.format("Validate card: Card %s is blocked or does not exist", number));
            throw new CardBlockedOrNotExistException();
        }
    }

    /**
     * Handles {@link CardBlockedOrNotExistException} and redirects to corresponding error page
     */
    @ExceptionHandler(CardBlockedOrNotExistException.class)
    public String handleCardBlockedOrNotExist(Exception ex) {
        return "redirect:/errorBlockedOrNotExist";
    }

}

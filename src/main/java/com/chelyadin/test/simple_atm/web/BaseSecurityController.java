package com.chelyadin.test.simple_atm.web;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Dmitriy Chelyadin
 */
public abstract class BaseSecurityController {
    protected String getCurrentCreditCardNumber() {
        return ((CreditCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNumber();
    }
}

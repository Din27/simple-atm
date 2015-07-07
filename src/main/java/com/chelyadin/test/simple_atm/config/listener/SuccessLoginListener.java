package com.chelyadin.test.simple_atm.config.listener;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @author Dmitriy Chelyadin
 */
@Component
public class SuccessLoginListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private static final Logger logger = LoggerFactory.getLogger(SuccessLoginListener.class);

    @Autowired
    private CreditCardService creditCardService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        CreditCard creditCard = (CreditCard) event.getAuthentication().getPrincipal();
        Integer failedLoginAttempts = creditCardService.resetFailedLoginAttempts(creditCard.getNumber());
        logger.info(String.format("PIN Entering succeeded (%s). Failed attempts reset, now: %d", creditCard.getNumber(), failedLoginAttempts));
    }
}
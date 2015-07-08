package com.chelyadin.test.simple_atm.config.listener;

import com.chelyadin.test.simple_atm.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * @author Dmitriy Chelyadin
 *
 * This listener listens for failed login (pin entering) events and updates failed login attempts in DB for the credit card
 * Once there is 4, the card is going to be locked by Spring Security
 */
@Component
public class FailedLoginListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private static final Logger logger = LoggerFactory.getLogger(FailedLoginListener.class);

    @Autowired
    private CreditCardService creditCardService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String number = (String) event.getAuthentication().getPrincipal();
        Integer failedLoginAttempts = creditCardService.incrementFailedLoginAttempts(number);
        logger.info(String.format("PIN Entering failed (%s). Failed attempts incremented, now: %d", number, failedLoginAttempts));
    }
}
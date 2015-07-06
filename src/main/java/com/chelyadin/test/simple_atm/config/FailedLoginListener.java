package com.chelyadin.test.simple_atm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author Dmitriy Chelyadin
 */
@Component
public class FailedLoginListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private static final Logger logger = LoggerFactory.getLogger(FailedLoginListener.class);

    @Autowired
    private HttpSession httpSession;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        Object number = event.getAuthentication().getPrincipal();
        logger.info("Failed pin code entering using Credit Card Number: " + number);
        httpSession.setAttribute("LAST_NUMBER", number);
    }
}

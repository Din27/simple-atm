package com.chelyadin.test.simple_atm.config.listener;

import com.chelyadin.test.simple_atm.service.DefaultTestDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Dmitriy Chelyadin
 *
 * This listener is needed to create default test data if we don't have it in DB
 */
@Component
public class ApplicationReadyListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationReadyListener.class);

    @Autowired
    private DefaultTestDataService defaultTestDataService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        defaultTestDataService.createDefaultTestDataIfNeeded();
    }

}

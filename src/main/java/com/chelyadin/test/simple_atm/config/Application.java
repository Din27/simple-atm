package com.chelyadin.test.simple_atm.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Dmitriy Chelyadin
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.chelyadin.test.simple_atm" })
@EnableWebMvc
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

}
package com.chelyadin.test.simple_atm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

/**
 * @author Dmitriy Chelyadin
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan(basePackages = { "com.chelyadin.test.simple_atm" })
public class Application extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("Configuring Application: SpringApplicationBuilder");
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    private static void logAllBeans(ApplicationContext ctx) {
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        StringBuilder sb = new StringBuilder("Beans:\n");
        for (String beanName : beanNames) {
            sb
                    .append("* ")
                    .append(beanName)
                    .append("\n");

        }
        logger.info(sb.toString());
    }

}
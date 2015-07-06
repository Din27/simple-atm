package com.chelyadin.test.simple_atm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Dmitriy Chelyadin
 */
@Controller
public class OperationsController {

    private static final Logger logger = LoggerFactory.getLogger(OperationsController.class);

    @RequestMapping("/operations")
    public String operations() {
        logger.info("Operations page request");
        return "operations";
    }
}

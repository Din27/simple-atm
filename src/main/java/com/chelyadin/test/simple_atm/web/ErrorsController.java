package com.chelyadin.test.simple_atm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Dmitriy Chelyadin
 */
@Controller
public class ErrorsController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorsController.class);

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String error() {
        logger.info("Error page request");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}

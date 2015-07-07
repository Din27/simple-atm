package com.chelyadin.test.simple_atm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("errorBlockedOrNotExist")
    public ModelAndView errorBlockedOrDoesNotExist() {
        logger.info("Error (card blocked/does not exist) page request");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg", "Error! Card is blocked or does not exist");
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}

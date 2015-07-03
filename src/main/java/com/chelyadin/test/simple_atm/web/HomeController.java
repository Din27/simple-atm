package com.chelyadin.test.simple_atm.web;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Dmitriy Chelyadin
 */
@Controller
public class HomeController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(ERROR_PATH)
    public String error() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}

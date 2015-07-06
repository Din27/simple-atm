package com.chelyadin.test.simple_atm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author Dmitriy Chelyadin
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView();

        String number = (String) httpSession.getAttribute("LAST_NUMBER");
        if (number == null) {
            logger.info("Login Page Request: Redirecting to Home Page to enter card number");
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        logger.info(String.format("Login Page Request: Card Number: %s)", number));
        modelAndView.addObject("number", number);

        if (error != null) {
            // TODO add left attempts info? or no?
            modelAndView.addObject("error", "Invalid PIN");
        }

        if (logout != null) {
            modelAndView.addObject("msg", "You've exited successfully");
        }

        modelAndView.setViewName("login");
        return modelAndView;

    }
}

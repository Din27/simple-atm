package com.chelyadin.test.simple_atm.web;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginController extends BaseSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private CreditCardService creditCardService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView();

        String number = (String) httpSession.getAttribute("LAST_NUMBER");
        if (number == null) {
            logger.info("Login Page Request: Redirecting to Home Page to enter card number");
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        validateCard(number);

        logger.info(String.format("Login Page Request: Card Number: %s)", number));
        modelAndView.addObject("number", number);

        if (error != null) {
            modelAndView.addObject("error", String.format("Error! Invalid PIN (attempts left: %d)",
                            creditCardService.getFailedLoginAttemptsLeft(number)));
        }

        modelAndView.setViewName("login");
        return modelAndView;
    }

}

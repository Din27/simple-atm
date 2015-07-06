package com.chelyadin.test.simple_atm.web;

import com.chelyadin.test.simple_atm.form.CreditCardNumberForm;
import com.chelyadin.test.simple_atm.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author Dmitriy Chelyadin
 */
@Controller
public class HomeController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private static final String ERROR_PATH = "/error";

    CreditCardService creditCardService;

    @Autowired
    public HomeController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        logger.info("Home page request");
        return "home";
    }

    @RequestMapping(value = "/enter_card", method = RequestMethod.POST)
    public String submitCreditCardNumber(
            @ModelAttribute CreditCardNumberForm form,
            HttpSession httpSession) {


        if (!creditCardService.checkCreditCard(form.getNumber())) {
            logger.info(String.format("Enter card request: Card %s not found", form.getNumber()));
            return "redirect:/error";
        }

        logger.info(String.format("Enter card request: Card %s found", form.getNumber()));
        httpSession.setAttribute("LAST_NUMBER", form.getNumber());
        return "redirect:/login";

    }

    @RequestMapping("/secured")
    public String secured() {
        logger.info("Secured page request");
        return "secured";
    }

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

package com.chelyadin.test.simple_atm.web;

import com.chelyadin.test.simple_atm.form.CreditCardNumberForm;
import com.chelyadin.test.simple_atm.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author Dmitriy Chelyadin
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    CreditCardService creditCardService;

    @Autowired
    public HomeController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(@RequestParam(value = "logout", required = false) String logout) {
        logger.info(String.format("Home page request"));

        ModelAndView modelAndView = new ModelAndView();
        if (logout != null) {
            modelAndView.addObject("msg", "You've exited successfully");
        }
        modelAndView.setViewName("home");

        return modelAndView;
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

}

package com.chelyadin.test.simple_atm.web;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @RequestMapping("/balance")
    public ModelAndView balance() {
        logger.info("Balance page request");

        ModelAndView modelAndView = new ModelAndView();

        CreditCard creditCard = (CreditCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("number", creditCard.getNumber());
        modelAndView.addObject("amount", creditCard.getAmount());

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        modelAndView.addObject("currentDate", dateFormat.format(new Date()));

        modelAndView.setViewName("balance");
        return modelAndView;
    }
}

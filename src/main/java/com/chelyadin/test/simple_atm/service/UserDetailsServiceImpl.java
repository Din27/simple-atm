package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.repository.CreditCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Dmitriy Chelyadin
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private CreditCardRepo creditCardRepo;

    @Autowired
    public UserDetailsServiceImpl(CreditCardRepo creditCardRepo) {
        this.creditCardRepo = creditCardRepo;
    }

    @Override
    @Transactional
    public CreditCard loadUserByUsername(String number) {
        CreditCard creditCard = creditCardRepo.findOne(number);
        if (creditCard == null) {
            throw new UsernameNotFoundException("Credit Card not found");
        }
        return creditCard;
    }
}

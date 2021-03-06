package com.chelyadin.test.simple_atm.service;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import com.chelyadin.test.simple_atm.repository.CreditCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author Dmitriy Chelyadin
 *
 * Service for Spring Security to work with CreditCard entity as a UserDetails
 */
@Service
public class UserDetailsServiceImpl extends BaseService implements UserDetailsService {

    private CreditCardRepo creditCardRepo;

    @Autowired
    public UserDetailsServiceImpl(CreditCardRepo creditCardRepo) {
        this.creditCardRepo = creditCardRepo;
    }

    @Override
    public CreditCard loadUserByUsername(String number) {
        CreditCard creditCard = creditCardRepo.findOne(number);
        if (creditCard == null) {
            throw new UsernameNotFoundException("Credit Card not found");
        }
        return creditCard;
    }
}

package com.chelyadin.test.simple_atm.repository;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Chelyadin
 */
@Repository
public interface CreditCardRepo extends CrudRepository<CreditCard, String> {
}

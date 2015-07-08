package com.chelyadin.test.simple_atm.repository;

import com.chelyadin.test.simple_atm.domain.CreditCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Chelyadin
 *
 * JPA Repository to work with {@link CreditCard} entity and Credit Cards table
 */
@Repository
public interface CreditCardRepo extends CrudRepository<CreditCard, String> {
}

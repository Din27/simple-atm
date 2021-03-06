package com.chelyadin.test.simple_atm.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dmitriy Chelyadin
 *
 * Base service to set up transactions just in one place
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
public abstract class BaseService {
}

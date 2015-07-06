package com.chelyadin.test.simple_atm.repository;

import com.chelyadin.test.simple_atm.domain.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Chelyadin
 */
@Repository
public interface OperationRepo extends CrudRepository<Operation, Long> {
}

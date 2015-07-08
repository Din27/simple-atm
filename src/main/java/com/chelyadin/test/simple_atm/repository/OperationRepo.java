package com.chelyadin.test.simple_atm.repository;

import com.chelyadin.test.simple_atm.domain.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Chelyadin
 *
 * JPA Repository to work with {@link Operation} entity and update the Operation History table
 */
@Repository
public interface OperationRepo extends CrudRepository<Operation, Long> {
}

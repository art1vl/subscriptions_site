package com.artsykov.backend.repository;

import com.artsykov.backend.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
    @Query(value = "SELECT *  FROM backend.customer WHERE log_in_inf = ?1",
            nativeQuery=true)
    CustomerEntity findCustomerByLogInInfId(int logInInfId);
}

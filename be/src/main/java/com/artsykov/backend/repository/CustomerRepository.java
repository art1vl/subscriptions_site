package com.artsykov.backend.repository;

import com.artsykov.backend.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findByLogInInfIdLogInInf(@Param("log_in_inf") int logInInfId);

    CustomerEntity findByIdCustomer(@Param("id_customer") int customerId);

    Page<CustomerEntity> findAll(Pageable pageable);
}

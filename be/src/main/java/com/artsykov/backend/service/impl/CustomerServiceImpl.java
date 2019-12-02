package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.repository.CustomerRepository;
import com.artsykov.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerEntity getCustomerByLogInInfId (int logInInfId) {
        return repository.findCustomerByLogInInfId(logInInfId);
    }

    @Override
    public CustomerEntity updateCustomer(CustomerEntity customerEntity) {
        return repository.save(customerEntity);
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return repository.save(customer);
    }
}

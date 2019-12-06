package com.artsykov.backend.service;

import com.artsykov.backend.entity.CustomerEntity;

public interface CustomerService {
    CustomerEntity saveCustomer(CustomerEntity customer);

    CustomerEntity findCustomerByLogInInfId(int logInInfId);

    CustomerEntity updateCustomer(CustomerEntity customerEntity);

    CustomerEntity saveCustomerWallet(CustomerEntity customerEntity);

    CustomerEntity findCustomerById(int customerId);
}

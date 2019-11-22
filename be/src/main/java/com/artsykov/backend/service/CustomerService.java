package com.artsykov.backend.service;

import com.artsykov.backend.entity.CustomerEntity;

public interface CustomerService {
    CustomerEntity saveCustomer (CustomerEntity customer);

    CustomerEntity getCustomerByLogInInfId (int logInInfId);

    CustomerEntity updateCustomer (CustomerEntity customerEntity);
}

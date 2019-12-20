package com.artsykov.backend.service;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.model.CustomerPageModel;

public interface CustomerService {
    CustomerEntity saveCustomer(CustomerEntity customer);

    CustomerEntity findCustomerByLogInInfId(int logInInfId);

    void updateCustomer(CustomerEntity customerEntity);

    CustomerEntity saveCustomerWallet(CustomerEntity customerEntity);

    CustomerEntity findCustomerById(int customerId);

    CustomerPageModel findAllByPage(int pageNumber, int amount);

    void changeStatus(CustomerEntity customerEntity);

    CustomerEntity liquidateDebt(CustomerEntity customerEntity);
}

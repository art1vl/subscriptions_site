package com.artsykov.fapi.service;

import com.artsykov.fapi.models.CustomerModel;
import com.artsykov.fapi.models.CustomerOrErrorsModel;
import com.artsykov.fapi.models.WalletModel;

public interface CustomerDataService {
    CustomerOrErrorsModel checkAndSaveCustomer (CustomerModel customer);

    CustomerModel findCustomerByLogInInfId(int logInInfId);

    CustomerModel findCustomerById(int id);

    CustomerModel[] findAll();

   // CustomerOrErrorsModel findCustomerByEmail(String email, String password);

    void updateCustomerPersonalInf(CustomerModel customerModel);

    CustomerModel saveCustomerWallet(CustomerModel customerModel);

    WalletModel findWalletByCustomerId(int customerId);
}

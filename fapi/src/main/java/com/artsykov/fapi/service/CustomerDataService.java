package com.artsykov.fapi.service;

import com.artsykov.fapi.models.CustomerModel;
import com.artsykov.fapi.models.CustomerOrErrorsModel;

public interface CustomerDataService {
    CustomerModel checkAndSaveCustomer (CustomerModel customer);

    CustomerModel findCustomerById(Long idCustomer);

   // CustomerOrErrorsModel findCustomerByEmail(String email, String password);

    CustomerOrErrorsModel updateCustomerPersonalInf(CustomerModel customerModel);
}

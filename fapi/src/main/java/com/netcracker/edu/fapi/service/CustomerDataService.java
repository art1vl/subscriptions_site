package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.CustomerModel;

public interface CustomerDataService {
    boolean checkAndSaveCustomer (CustomerModel customer);
}

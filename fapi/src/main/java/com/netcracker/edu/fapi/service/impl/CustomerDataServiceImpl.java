package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.CustomerModel;
import com.netcracker.edu.fapi.service.CustomerDataService;
import org.springframework.stereotype.Service;

@Service
public class CustomerDataServiceImpl implements CustomerDataService {

    @Override
    public boolean checkAndSaveCustomer(CustomerModel customer) {
        System.out.println(customer);
        return true;
    }

    private boolean isCustomerValid (CustomerModel customer) {
        return true;
    }
}

package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CustomerModel;
import com.netcracker.edu.fapi.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerDataService customerDataService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Boolean> checkAndSaveCustomer (@RequestBody CustomerModel customerModel) {
        if (customerModel != null) {
            return ResponseEntity.ok(customerDataService.checkAndSaveCustomer(customerModel));
        }
        else {
            return ResponseEntity.ok(false);
        }
    }
}

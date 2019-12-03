package com.artsykov.backend.controller;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/log/in/inf/{id}")
    public ResponseEntity<CustomerEntity> getCustomerByLogInInfId(@PathVariable("id") String logInInfId) throws InterruptedException {
        int intId = Integer.valueOf(logInInfId);
        return ResponseEntity.ok(customerService.findCustomerByLogInInfId(intId));
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable("id") String customerId) {
        int intCustomerId = Integer.valueOf(customerId);
        return ResponseEntity.ok(customerService.findCustomerById(intCustomerId));
    }

    @PostMapping
    public ResponseEntity<CustomerEntity> saveCustomer(@RequestBody CustomerEntity customerEntity) {
        return ResponseEntity.ok(customerService.saveCustomer(customerEntity));
    }

    @PostMapping(value = "/wallet")
    public ResponseEntity<CustomerEntity> saveCustomerWallet(@RequestBody CustomerEntity customerEntity) {
        return ResponseEntity.ok(customerService.saveCustomerWallet(customerEntity));
    }

    @PutMapping(value = "/update")
    public void updateCustomer(@RequestBody CustomerEntity customerEntity) {
        customerService.updateCustomer(customerEntity);
    }
}

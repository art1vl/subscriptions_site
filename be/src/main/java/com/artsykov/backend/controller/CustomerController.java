package com.artsykov.backend.controller;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.repository.LogInInfRepository;
import com.artsykov.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;
    private LogInInfRepository repository;

    @Autowired
    public CustomerController (CustomerService customerService, LogInInfRepository repository) {
        this.customerService = customerService;
        this.repository = repository;
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<CustomerEntity> getCustomerByLogInInfId(@PathVariable("id") String logInInfId) throws InterruptedException {
        Integer intId = Integer.valueOf(logInInfId);
        return ResponseEntity.ok(customerService.getCustomerByLogInInfId(intId));
    }

    @PostMapping
    public ResponseEntity<CustomerEntity> saveCustomer(@RequestBody CustomerEntity customerEntity) {
        return ResponseEntity.ok(customerService.saveCustomer(customerEntity));
    }

//    @PostMapping(value = "/update")
//    public ResponseEntity<CustomerEntity> updateCustomer(@RequestBody CustomerEntity customerEntity) {
//        return ResponseEntity.ok(customerService.updateCustomer(customerEntity));
//    }

    @PutMapping(value = "/update")
    public void updateCustomer(@RequestBody CustomerEntity customerEntity) {
        customerService.updateCustomer(customerEntity);
    }
}

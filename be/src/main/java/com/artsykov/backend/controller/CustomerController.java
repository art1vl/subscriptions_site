package com.artsykov.backend.controller;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.model.CustomerPageModel;
import com.artsykov.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/log/in/inf/{id}")
    public ResponseEntity<CustomerEntity> getCustomerByLogInInfId(@PathVariable("id") Integer logInInfId) throws InterruptedException {
        return ResponseEntity.ok(customerService.findCustomerByLogInInfId(logInInfId));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable("id") Integer customerId) {
        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<CustomerPageModel> getAllByPage(@RequestParam("page") Integer pageNumber,
                                                          @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(customerService.findAllByPage(pageNumber, amount));
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

    @PutMapping(value = "/status")
    public void changeCustomerStatus(@RequestBody CustomerEntity customerEntity) {
        customerService.changeStatus(customerEntity);
    }
}

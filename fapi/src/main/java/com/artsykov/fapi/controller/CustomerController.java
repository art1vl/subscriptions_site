package com.artsykov.fapi.controller;

import com.artsykov.fapi.models.CustomerModel;
import com.artsykov.fapi.models.CustomerOrErrorsModel;
import com.artsykov.fapi.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerDataService customerDataService;

//    @RequestMapping(value = "/{id}")
//    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable String id) throws InterruptedException {
//        Long customerId = Long.valueOf(id);
//        return ResponseEntity.ok(customerDataService.findCustomerById(customerId));
////        WalletEntity walletByIdWallet = new WalletEntity(1, 100, 1111222233334444L, new Timestamp(1570000000000L), 123, "Ivan Ivanov");
////        CustomerModel customerModel = new CustomerModel(1, "Jon", "Smith", 20, "jon@mail.ru", null, walletByIdWallet, true, 1);
////        return ResponseEntity.ok(customerModel);
//    }

//    @RequestMapping(value = "/{email}/{password}")
//    public ResponseEntity<CustomerOrErrorsModel> getCustomerByEmail(@PathVariable("email") String email, @PathVariable("password") String password) throws InterruptedException {
//        return ResponseEntity.ok(customerDataService.findCustomerByEmail(email, password));
//    }

//    @PostMapping(value = "/sign/in")
//    public ResponseEntity<CustomerOrErrorsModel> getCustomerByEmail(@RequestBody LogInParam logInParam) throws InterruptedException {
//        return ResponseEntity.ok(customerDataService.findCustomerByEmail(logInParam.getEmail(), logInParam.getPassword()));
//    }

    @PostMapping
    public ResponseEntity<CustomerModel> checkAndSaveCustomer(@RequestBody CustomerModel customerModel) {
        if (customerModel != null) {
            return ResponseEntity.ok(customerDataService.checkAndSaveCustomer(customerModel));
        } else {
            return ResponseEntity.ok(null);
        }
    }

    //todo
    @PutMapping
    public ResponseEntity<CustomerOrErrorsModel> updateCustomerPersonalInf(@RequestBody CustomerModel customerModel) {
//        if (errors.hasErrors()) {
//
//        }
      //  CustomerOrErrorsModel customerOrErrorsModel = new CustomerOrErrorsModel(customerModel, null);
        return ResponseEntity.ok(customerDataService.updateCustomerPersonalInf(customerModel));
    }
}

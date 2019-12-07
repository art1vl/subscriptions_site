package com.artsykov.fapi.controller;

import com.artsykov.fapi.controller.handler.HandlerService;
import com.artsykov.fapi.models.CustomerModel;
import com.artsykov.fapi.models.CustomerOrErrorsModel;
import com.artsykov.fapi.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerDataService customerDataService;

    @Autowired
    private HandlerService handlerService;

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

    //todo
//    @GetMapping
//    public ResponseEntity<CustomerModel[]> findAll() {
//
//    }

    @Secured("ROLE_CUSTOMER")
    @GetMapping(value = "/{id}")
    public  ResponseEntity<CustomerModel> getCustomerByLogInInfId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(customerDataService.findCustomerByLogInInfId(id));
    }

    @PostMapping
    public ResponseEntity<CustomerOrErrorsModel> checkAndSaveCustomer(@RequestBody @Valid CustomerModel customerModel) {
        return ResponseEntity.ok(new CustomerOrErrorsModel(customerDataService.checkAndSaveCustomer(customerModel)));
    }

    @Secured("ROLE_CUSTOMER")
    @PostMapping(value = "/wallet")
    public ResponseEntity<CustomerOrErrorsModel> saveCustomerWallet(@RequestBody @Valid CustomerModel customerModel) {
        return ResponseEntity.ok(new CustomerOrErrorsModel(customerDataService.saveCustomerWallet(customerModel)));
    }

    @Secured("ROLE_CUSTOMER")
    @PutMapping
    public ResponseEntity<CustomerOrErrorsModel> updateCustomerPersonalInf(@RequestBody @Valid CustomerModel customerModel) {
        customerDataService.updateCustomerPersonalInf(customerModel);
        return ResponseEntity.ok(new CustomerOrErrorsModel(customerModel));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomerOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(new CustomerOrErrorsModel(handlerService.handleMethodArgumentNotValid(ex)));
    }
}

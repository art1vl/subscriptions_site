package com.artsykov.fapi.controller;

import com.artsykov.fapi.models.CustomerModel;
import com.artsykov.fapi.models.CustomerOrErrorsModel;
import com.artsykov.fapi.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

    //todo
//    @GetMapping
//    public ResponseEntity<CustomerModel[]> findAll() {
//
//    }

    @PostMapping
    public ResponseEntity<CustomerModel> checkAndSaveCustomer(@RequestBody @Valid CustomerModel customerModel) {
        if (customerModel != null) {
            return ResponseEntity.ok(customerDataService.checkAndSaveCustomer(customerModel));
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping(value = "/wallet")
    public ResponseEntity<CustomerOrErrorsModel> saveCustomerWallet(@RequestBody @Valid CustomerModel customerModel) {
        CustomerOrErrorsModel customerOrErrorsModel = new CustomerOrErrorsModel();
        customerOrErrorsModel.setCustomerModel(customerDataService.saveCustomerWallet(customerModel));
        return ResponseEntity.ok(customerOrErrorsModel);
    }

    @PutMapping
    public ResponseEntity<CustomerOrErrorsModel> updateCustomerPersonalInf(@RequestBody @Valid CustomerModel customerModel) {
        customerDataService.updateCustomerPersonalInf(customerModel);
        CustomerOrErrorsModel customerOrErrorsModel = new CustomerOrErrorsModel();
        customerOrErrorsModel.setCustomerModel(customerModel);
        return ResponseEntity.ok(customerOrErrorsModel);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomerOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        CustomerOrErrorsModel customerOrErrorsModel = new CustomerOrErrorsModel();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        customerOrErrorsModel.setErrors(errors);
        return ResponseEntity.ok(customerOrErrorsModel);
    }
}

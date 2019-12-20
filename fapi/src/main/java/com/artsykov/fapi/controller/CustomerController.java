package com.artsykov.fapi.controller;

import com.artsykov.fapi.controller.handler.HandlerService;
import com.artsykov.fapi.models.CustomerModel;
import com.artsykov.fapi.models.CustomerOrErrorsModel;
import com.artsykov.fapi.models.CustomerPageModel;
import com.artsykov.fapi.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerDataService customerDataService;
    private HandlerService handlerService;

    @Autowired
    public CustomerController(CustomerDataService customerDataService, HandlerService handlerService) {
        this.customerDataService = customerDataService;
        this.handlerService = handlerService;
    }

    @Secured("ROLE_CUSTOMER")
    @GetMapping(value = "/log/in/inf/{id}")
    public  ResponseEntity<CustomerModel> getCustomerByLogInInfId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(customerDataService.findCustomerByLogInInfId(id));
    }

    @Secured("ROLE_CUSTOMER")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(customerDataService.findCustomerById(id));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/all")
    public ResponseEntity<CustomerPageModel> findAllByPage(@RequestParam("page") Integer pageNumber,
                                                           @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(customerDataService.findAllByPage(pageNumber, amount));
    }

    @PostMapping
    public ResponseEntity<CustomerOrErrorsModel> checkAndSaveCustomer(@RequestBody @Valid CustomerModel customerModel) {
        return ResponseEntity.ok(customerDataService.checkAndSaveCustomer(customerModel));
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

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/status")
    public ResponseEntity<CustomerOrErrorsModel> changeCustomerStatus(@RequestBody @Valid CustomerModel customerModel) {
        customerDataService.changeStatus(customerModel);
        return ResponseEntity.ok(new CustomerOrErrorsModel(customerModel));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomerOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(new CustomerOrErrorsModel(handlerService.handleMethodArgumentNotValid(ex)));
    }
}

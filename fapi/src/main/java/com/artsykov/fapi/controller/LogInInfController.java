package com.artsykov.fapi.controller;

import com.artsykov.fapi.models.CustomerOrCompanyOrAdminOrErrorsModel;
import com.artsykov.fapi.service.LogInInfDataService;
import com.artsykov.fapi.models.LogInParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/logInInf")
public class LogInInfController {

    @Autowired
    private LogInInfDataService logInInfDataService;

    @PostMapping(value = "/sign/in")
    public ResponseEntity<CustomerOrCompanyOrAdminOrErrorsModel> getUserByEmail(@RequestBody @Valid LogInParam logInParam, BindingResult bindingResult) throws InterruptedException {

        return ResponseEntity.ok(logInInfDataService.findUserByEmail(logInParam.getEmail(), logInParam.getPassword()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomerOrCompanyOrAdminOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        CustomerOrCompanyOrAdminOrErrorsModel customerOrCompanyOrAdminOrErrorsModel = new CustomerOrCompanyOrAdminOrErrorsModel();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        customerOrCompanyOrAdminOrErrorsModel.setErrors(errors);
        return ResponseEntity.ok(customerOrCompanyOrAdminOrErrorsModel);
    }
}

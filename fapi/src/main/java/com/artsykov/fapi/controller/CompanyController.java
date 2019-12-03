package com.artsykov.fapi.controller;

import com.artsykov.fapi.models.CompanyModel;
import com.artsykov.fapi.models.CompanyOrErrorsModel;
import com.artsykov.fapi.models.CustomerOrErrorsModel;
import com.artsykov.fapi.service.CompanyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyDataService companyDataService;

    @PostMapping
    public ResponseEntity<CompanyModel> createCompany(@RequestBody CompanyModel companyModel) {
        return ResponseEntity.ok(companyDataService.saveCompany(companyModel));
    }

    @PostMapping(value = "/wallet")
    public ResponseEntity<CompanyModel> saveWallet(@RequestBody CompanyModel companyModel) {
        return ResponseEntity.ok(companyDataService.saveWallet(companyModel));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CompanyOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        CompanyOrErrorsModel companyOrErrorsModel = new CompanyOrErrorsModel();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        companyOrErrorsModel.setErrors(errors);
        return ResponseEntity.ok(companyOrErrorsModel);
    }
}

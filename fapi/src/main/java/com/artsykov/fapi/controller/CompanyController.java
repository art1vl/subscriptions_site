package com.artsykov.fapi.controller;

import com.artsykov.fapi.controller.handler.HandlerService;
import com.artsykov.fapi.models.CompanyModel;
import com.artsykov.fapi.models.CompanyOrErrorsModel;
import com.artsykov.fapi.models.CustomerOrErrorsModel;
import com.artsykov.fapi.service.CompanyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyDataService companyDataService;

    @Autowired
    private HandlerService handlerService;

    @Secured("ROLE_COMPANY")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyModel> getCompanyByLogInInfId(@PathVariable("id") Integer logInInfId) {
        return ResponseEntity.ok(companyDataService.getCompanyByLogInInfId(logInInfId));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<CompanyOrErrorsModel> createCompany(@RequestBody CompanyModel companyModel) {
        return ResponseEntity.ok(new CompanyOrErrorsModel(companyDataService.saveCompany(companyModel)));
    }

    @Secured("ROLE_COMPANY")
    @PostMapping(value = "/wallet")
    public ResponseEntity<CompanyOrErrorsModel> saveWallet(@RequestBody CompanyModel companyModel) {
        return ResponseEntity.ok(new CompanyOrErrorsModel(companyDataService.saveWallet(companyModel)));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CompanyOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(new CompanyOrErrorsModel(handlerService.handleMethodArgumentNotValid(ex)));
    }
}

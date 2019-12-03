package com.artsykov.backend.controller;

import com.artsykov.backend.entity.CompanyEntity;
import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/log/in/inf/{id}")
    public ResponseEntity<CompanyEntity> getCompanyByLogInInfId(@PathVariable("id") String logInInfId) throws InterruptedException {
        Integer intId = Integer.valueOf(logInInfId);
        return ResponseEntity.ok(companyService.findCompanyByLogInInfId(intId));
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<CompanyEntity> getCompanyById(@PathVariable("id") String companyId) {
        int intCompanyId = Integer.valueOf(companyId);
        return ResponseEntity.ok(companyService.findCompany(intCompanyId));
    }

    @PostMapping(value = "/wallet")
    public ResponseEntity<CompanyEntity> saveWallet(@RequestBody CompanyEntity companyEntity) {
        return ResponseEntity.ok(companyService.saveWallet(companyEntity));
    }

    @PostMapping
    public ResponseEntity<CompanyEntity> saveCompany(@RequestBody CompanyEntity companyEntity) {
        return ResponseEntity.ok(companyService.saveCompany(companyEntity));
    }
}

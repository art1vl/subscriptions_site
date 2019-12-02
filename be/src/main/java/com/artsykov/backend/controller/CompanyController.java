package com.artsykov.backend.controller;

import com.artsykov.backend.entity.CompanyEntity;
import com.artsykov.backend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyEntity> saveCustomer(@RequestBody CompanyEntity companyEntity) {
        return ResponseEntity.ok(companyService.saveCompany(companyEntity));
    }
}

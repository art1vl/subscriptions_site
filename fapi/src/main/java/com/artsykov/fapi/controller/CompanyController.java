package com.artsykov.fapi.controller;

import com.artsykov.fapi.models.CompanyModel;
import com.artsykov.fapi.models.CompanyOrErrorsModel;
import com.artsykov.fapi.service.CompanyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyDataService companyDataService;

    @PostMapping
    public ResponseEntity<CompanyOrErrorsModel> createCompany(@RequestBody CompanyModel companyModel) {
        return ResponseEntity.ok(companyDataService.saveCompany(companyModel));
    }
}

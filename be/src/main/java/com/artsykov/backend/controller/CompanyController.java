package com.artsykov.backend.controller;

import com.artsykov.backend.entity.CompanyEntity;
import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.model.CompanyPageModel;
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

    @GetMapping(value = "/log/in/inf/{id}")
    public ResponseEntity<CompanyEntity> getCompanyByLogInInfId(@PathVariable("id") Integer logInInfId) throws InterruptedException {
        return ResponseEntity.ok(companyService.findCompanyByLogInInfId(logInInfId));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<CompanyPageModel> getAllByPage(@RequestParam("page") Integer pageNumber,
                                                         @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(companyService.findAllByPage(pageNumber, amount));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyEntity> getCompanyById(@PathVariable("id") Integer companyId) {
        return ResponseEntity.ok(companyService.findCompany(companyId));
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

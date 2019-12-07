package com.artsykov.fapi.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
public class CustomerOrCompanyOrAdminOrErrorsModel {
    private CustomerModel customerModel;
    private CompanyModel companyModel;
    private AdminModel adminModel;
    private Map<String, String> errors;
    private String token;

    public CustomerOrCompanyOrAdminOrErrorsModel(String token) {
        this.token = token;
    }

    public CustomerOrCompanyOrAdminOrErrorsModel() {}

    public CustomerOrCompanyOrAdminOrErrorsModel(Map<String, String> errors) {
        this.errors = errors;
    }
}

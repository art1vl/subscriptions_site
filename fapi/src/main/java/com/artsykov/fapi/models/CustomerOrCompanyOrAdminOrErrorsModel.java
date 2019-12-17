package com.artsykov.fapi.models;

import lombok.Data;

import java.util.Map;

@Data
public class CustomerOrCompanyOrAdminOrErrorsModel {
    private CustomerModel customerModel;
    private CompanyModel companyModel;
    private AdminModel adminModel;
    private Map<String, String> errors;

    public CustomerOrCompanyOrAdminOrErrorsModel() {}

    public CustomerOrCompanyOrAdminOrErrorsModel(Map<String, String> errors) {
        this.errors = errors;
    }
}

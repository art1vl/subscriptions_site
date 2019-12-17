package com.artsykov.fapi.models;

import lombok.Data;

import java.util.Map;

@Data
public class CompanyOrErrorsModel {
    private CompanyModel companyModel;
    private Map<String, String> errors;

    public CompanyOrErrorsModel() {}

    public CompanyOrErrorsModel(Map<String, String> errors) {
        this.errors = errors;
    }

    public CompanyOrErrorsModel(CompanyModel companyModel) {
        this.companyModel = companyModel;
    }
}

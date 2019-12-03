package com.artsykov.fapi.models;

import lombok.Data;

import java.util.Map;

@Data
public class CompanyOrErrorsModel {
    private CompanyModel companyModel;
    private Map<String, String> errors;
}

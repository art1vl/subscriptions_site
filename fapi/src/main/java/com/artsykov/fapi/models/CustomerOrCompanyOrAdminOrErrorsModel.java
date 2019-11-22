package com.artsykov.fapi.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class CustomerOrCompanyOrAdminOrErrorsModel {
    private CustomerModel customerModel;
    private CompanyModel companyModel;
    private AdminModel adminModel;
    private Map<String, String> errors;
}

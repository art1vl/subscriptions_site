package com.artsykov.fapi.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
public class CustomerOrErrorsModel {
    private CustomerModel customerModel;
    private Map<String, String> errors;

    public CustomerOrErrorsModel() {}

    public CustomerOrErrorsModel(CustomerModel customerModel, Map<String, String> errors) {
        this.customerModel = customerModel;
        this.errors = errors;
    }
}

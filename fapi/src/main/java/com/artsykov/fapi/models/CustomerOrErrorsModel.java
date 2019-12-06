package com.artsykov.fapi.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
public class CustomerOrErrorsModel {
    private CustomerModel customerModel;
    private Map<String, String> errors;

    public CustomerOrErrorsModel() {}

    public CustomerOrErrorsModel(Map<String, String> errors) {
        this.errors = errors;
    }

    public CustomerOrErrorsModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }
}

package com.artsykov.fapi.validator;

import com.artsykov.fapi.models.CustomerModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerValidator implements Validator {

    public boolean supports(Class clazz) {
        return CustomerModel.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");

    }

    public void validatorUpdatePersonalInf(CustomerModel customerModel) {

    }
}

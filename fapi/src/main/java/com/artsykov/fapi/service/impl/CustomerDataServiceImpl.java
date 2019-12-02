package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.CustomerConverter;
import com.artsykov.fapi.entity.CustomerEntity;
import com.artsykov.fapi.validator.CustomerValidator;
import com.artsykov.fapi.models.CustomerModel;
import com.artsykov.fapi.models.CustomerOrErrorsModel;
import com.artsykov.fapi.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerDataServiceImpl implements CustomerDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    CustomerValidator customerValidator;

    @Override
    public CustomerModel checkAndSaveCustomer(CustomerModel customer) {
//        Errors errors = new ValidationErrors();
//                customerValidator.validate(customer, );
        if (isCustomerValid(customer)) {
            RestTemplate restTemplate = new RestTemplate();
            CustomerEntity customerEntity = CustomerConverter.convertFromFrontToBack(customer);
            return CustomerConverter.convertFromBackToFront(restTemplate.postForEntity(backendServerUrl + "/api/customer",
                    customerEntity, CustomerEntity.class).getBody());
        }
        return null;
    }

    @Override
    public CustomerModel findCustomerById(Long idCustomer) {
        RestTemplate restTemplate = new RestTemplate();
        return CustomerConverter.convertFromBackToFront(restTemplate.getForObject(backendServerUrl + "/api/customer/" + idCustomer, CustomerEntity.class));
    }

//    @Override
//    public CustomerOrErrorsModel findCustomerByEmail(String email, String password) {
//        CustomerOrErrorsModel customerOrErrorsModel = new CustomerOrErrorsModel();
//        Map<String, String> errorsMap = new HashMap<>();
//        RestTemplate restTemplate = new RestTemplate();
//        LogInInfEntity logInInfEntity = restTemplate.getForObject(backendServerUrl + "/api/log_in_inf/" + email, LogInInfEntity.class);
//        if (logInInfEntity != null) {
//            if (logInInfEntity.getPassword().equals(password)) {
//                customerOrErrorsModel.setCustomerModel(CustomerConverter.convertFromBackToFront(restTemplate.getForObject(
//                        backendServerUrl + "/api/customer/" + logInInfEntity.getIdLogInInf(), CustomerEntity.class)));
//            }
//            else {
//                errorsMap.put("password", "incorrect password");
//            }
//        }
//        else {
//            errorsMap.put("email", "email wasn't found");
//        }
//        if (!errorsMap.isEmpty()) {
//            customerOrErrorsModel.setErrors(errorsMap);
//        }
//        return customerOrErrorsModel;
//    }

    @Override
    public CustomerOrErrorsModel updateCustomerPersonalInf(CustomerModel customerModel) {
        CustomerOrErrorsModel customerOrErrorsModel = new CustomerOrErrorsModel();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl + "/api/customer/update", customerModel);
        return customerOrErrorsModel;
    }

    private boolean isCustomerValid(CustomerModel customer) {
        return true;
    }
}

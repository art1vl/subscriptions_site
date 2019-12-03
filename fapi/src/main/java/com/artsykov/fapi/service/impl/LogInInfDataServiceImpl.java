package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.CustomerConverter;
import com.artsykov.fapi.entity.CompanyEntity;
import com.artsykov.fapi.entity.CustomerEntity;
import com.artsykov.fapi.entity.LogInInfEntity;
import com.artsykov.fapi.converter.CompanyConverter;
import com.artsykov.fapi.models.AdminModel;
import com.artsykov.fapi.models.CustomerOrCompanyOrAdminOrErrorsModel;
import com.artsykov.fapi.service.LogInInfDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LogInInfDataServiceImpl implements LogInInfDataService {
    @Autowired
    private CustomerConverter customerConverter;

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private CompanyConverter companyConverter;

    @Override
    public CustomerOrCompanyOrAdminOrErrorsModel findUserByEmail(String email, String password) {
        CustomerOrCompanyOrAdminOrErrorsModel customerOrCompanyOrAdminOrErrorsModel = new CustomerOrCompanyOrAdminOrErrorsModel();
        AdminModel adminModel = new AdminModel();
        Map<String, String> errorsMap = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        LogInInfEntity logInInfEntity = restTemplate.getForObject(backendServerUrl + "/api/log_in_inf/" + email, LogInInfEntity.class);
        if (logInInfEntity != null) {
            if (logInInfEntity.getPassword().equals(password)) {
                switch (logInInfEntity.getRole()) {
                    case ADMIN:
                        adminModel.setIdLogInInf(logInInfEntity.getIdLogInInf());
                        customerOrCompanyOrAdminOrErrorsModel.setAdminModel(adminModel);
                        break;
                    case CUSTOMER:
                        customerOrCompanyOrAdminOrErrorsModel.setCustomerModel(customerConverter.convertFromBackToFront(restTemplate.getForObject(
                                backendServerUrl + "/api/customer/log/in/inf/" + logInInfEntity.getIdLogInInf(), CustomerEntity.class)));
                        break;
                    case COMPANY:
                        customerOrCompanyOrAdminOrErrorsModel.setCompanyModel(companyConverter.convertFromBackToFront(restTemplate.getForObject(
                                backendServerUrl + "/api/company/log/in/inf/" + logInInfEntity.getIdLogInInf(), CompanyEntity.class)));
                        break;

                }
            } else {
                errorsMap.put("password", "incorrect password");
            }
        } else {
            errorsMap.put("email", "email wasn't found");
        }
        if (!errorsMap.isEmpty()) {
            customerOrCompanyOrAdminOrErrorsModel.setErrors(errorsMap);
        }
        return customerOrCompanyOrAdminOrErrorsModel;
    }
}

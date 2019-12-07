package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.CustomerConverter;
import com.artsykov.fapi.converter.WalletConverter;
import com.artsykov.fapi.entity.CustomerEntity;
import com.artsykov.fapi.entity.WalletEntity;
import com.artsykov.fapi.validator.CustomerValidator;
import com.artsykov.fapi.models.CustomerModel;
import com.artsykov.fapi.models.CustomerOrErrorsModel;
import com.artsykov.fapi.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerDataServiceImpl implements CustomerDataService {
    private CustomerConverter customerConverter;
    private WalletConverter walletConverter;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    public CustomerDataServiceImpl(CustomerConverter customerConverter, WalletConverter walletConverter, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerConverter = customerConverter;
        this.walletConverter = walletConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //todo
    @Override
    public CustomerModel[] findAll() {
        RestTemplate restTemplate = new RestTemplate();
        return new CustomerModel[11];
    }

    @Override
    public CustomerModel checkAndSaveCustomer(CustomerModel customer) {
        RestTemplate restTemplate = new RestTemplate();
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        CustomerEntity customerEntity = customerConverter.convertFromFrontToBack(customer);
        return customerConverter.convertFromBackToFront(restTemplate.postForEntity(backendServerUrl + "/api/customer",
                customerEntity, CustomerEntity.class).getBody());

    }

    @Override
    public CustomerModel saveCustomerWallet(CustomerModel customerModel) {
        RestTemplate restTemplate = new RestTemplate();
        CustomerEntity customerEntity = customerConverter.convertFromFrontToBack(customerModel);
        WalletEntity walletEntity = customerEntity.getWalletByIdWallet();
        walletEntity = restTemplate.postForEntity(backendServerUrl + "/api/wallet", walletEntity, WalletEntity.class).getBody();
        customerEntity.setWalletByIdWallet(walletEntity);
        return customerConverter.convertFromBackToFront(restTemplate.postForEntity(backendServerUrl + "/api/customer/wallet", customerEntity, CustomerEntity.class).getBody());
    }

    @Override
    public CustomerModel findCustomerByLogInInfId(int logInInfId) {
        RestTemplate restTemplate = new RestTemplate();
        return customerConverter.convertFromBackToFront(restTemplate.getForObject(backendServerUrl + "/api/customer/log/in/inf/" + logInInfId, CustomerEntity.class));
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
    public void updateCustomerPersonalInf(CustomerModel customerModel) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl + "/api/customer/update", customerConverter.convertFromFrontToBack(customerModel));
    }

    private boolean isCustomerValid(CustomerModel customer) {
        return true;
    }
}

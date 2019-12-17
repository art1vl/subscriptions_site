package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.CustomerConverter;
import com.artsykov.fapi.converter.WalletConverter;
import com.artsykov.fapi.entity.CustomerEntity;
import com.artsykov.fapi.entity.WalletEntity;
import com.artsykov.fapi.models.CustomerModel;
import com.artsykov.fapi.models.CustomerOrErrorsModel;
import com.artsykov.fapi.models.WalletModel;
import com.artsykov.fapi.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerDataServiceImpl implements CustomerDataService {
    private CustomerConverter customerConverter;
    private WalletConverter walletConverter;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    public CustomerDataServiceImpl(CustomerConverter customerConverter,
                                   WalletConverter walletConverter,
                                   BCryptPasswordEncoder bCryptPasswordEncoder) {
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
    public CustomerModel findCustomerById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return customerConverter.convertFromBackToFront(restTemplate.getForObject(backendServerUrl + "/api/customer/" + id, CustomerEntity.class));
    }

    @Override
    public CustomerOrErrorsModel checkAndSaveCustomer(CustomerModel customer) {
        CustomerOrErrorsModel customerOrErrorsModel = new CustomerOrErrorsModel();
        RestTemplate restTemplate = new RestTemplate();
        boolean emailExists = restTemplate.getForObject(backendServerUrl + "/api/log/in/inf/exist/" + customer.getEmail(), Boolean.class);
        if (emailExists) {
            Map<String, String> errors = new HashMap<>();
            errors.put("email", "This email is busy");
            customerOrErrorsModel.setErrors(errors);
        }
        else {
            customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
            CustomerEntity customerEntity = customerConverter.convertFromFrontToBack(customer);
            customerOrErrorsModel.setCustomerModel(customerConverter.convertFromBackToFront(restTemplate.postForEntity(backendServerUrl + "/api/customer",
                    customerEntity, CustomerEntity.class).getBody()));
        }
        return customerOrErrorsModel;
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

    @Override
    public void updateCustomerPersonalInf(CustomerModel customerModel) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl + "/api/customer/update", customerConverter.convertFromFrontToBack(customerModel));
    }

    @Override
    public WalletModel findWalletByCustomerId(int customerId) {
        RestTemplate restTemplate = new RestTemplate();
        return walletConverter.convertFromBackToFront(restTemplate.getForObject(backendServerUrl +
                "/api/wallet/customer/" + customerId, WalletEntity.class));
    }
}

package com.artsykov.fapi.converter;

import com.artsykov.fapi.entity.CustomerEntity;
import com.artsykov.fapi.entity.LogInInfEntity;
import com.artsykov.fapi.entity.RoleEnum;
import com.artsykov.fapi.models.CustomerModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerConverter {

    //    public CustomerModel[] convertFromBackToFrontArray(CustomerEntity[] customerEntities) {
//        CustomerModel[] customerModels = new CustomerModel[customerEntities.length];
//        for (int i = 0; i < customerModels.length; i++) {
//            customerModels[i] = this.convertFromBackToFront(customerEntities[i]);
//        }
//        return customerModels;
//    }
    @Value("${backend.server.url}")
    private String backendServerUrl;

    public CustomerEntity convertFromFrontToBack(CustomerModel customerModel) {
        if (customerModel != null) {
            CustomerEntity customerEntity = new CustomerEntity();
            if (customerModel.getId() != 0) {
                customerEntity.setIdCustomer(customerModel.getId());
            }
            customerEntity.setName(customerModel.getName());
            customerEntity.setSurname(customerModel.getSurname());
            customerEntity.setAge(customerModel.getAge());
            if (customerModel.getIsActive() == 1) {
                customerEntity.setIsActive((byte) 1);
            } else {
                customerEntity.setIsActive((byte) 0);
            }
            customerEntity.setWalletByIdWallet(customerModel.getWallet());
            if (customerModel.getIdLogInInf() != 0) {
                RestTemplate restTemplate = new RestTemplate();
                CustomerEntity customerEntity1 = restTemplate.getForObject(backendServerUrl + "/api/customer/" + customerModel.getId(), CustomerEntity.class);
                customerEntity.setLogInInf(customerEntity1.getLogInInf());
            }
            else {
                LogInInfEntity logInInfEntity = new LogInInfEntity();
                logInInfEntity.setEmail(customerModel.getEmail());
                logInInfEntity.setRole(RoleEnum.CUSTOMER);
                logInInfEntity.setPassword(customerModel.getPassword());
                customerEntity.setLogInInf(logInInfEntity);
            }
            return customerEntity;
        }
        return null;
    }

    public CustomerModel convertFromBackToFront(CustomerEntity customerEntity) {
        if (customerEntity != null) {
            CustomerModel customerModel = new CustomerModel();
            customerModel.setId(customerEntity.getIdCustomer());
            customerModel.setAge(customerEntity.getAge());
            customerModel.setEmail(customerEntity.getLogInInf().getEmail());
            customerModel.setName(customerEntity.getName());
            customerModel.setSurname(customerEntity.getSurname());
            customerModel.setPassword(null);
            customerModel.setWallet(customerEntity.getWalletByIdWallet());
            customerModel.setIsActive(customerEntity.getIsActive());
            customerModel.setIdLogInInf(customerEntity.getLogInInf().getIdLogInInf());
            return customerModel;
        }
        return null;
    }
}

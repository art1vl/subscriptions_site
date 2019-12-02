package com.artsykov.fapi.converter;

import com.artsykov.fapi.entity.CustomerEntity;
import com.artsykov.fapi.entity.LogInInfEntity;
import com.artsykov.fapi.entity.RoleEnum;
import com.artsykov.fapi.models.CustomerModel;

public class CustomerConverter {

    public static CustomerEntity convertFromFrontToBack(CustomerModel customerModel) {
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
            LogInInfEntity logInInfEntity = new LogInInfEntity();
            if (customerModel.getIdLogInInf() != 0) {
                logInInfEntity.setIdLogInInf(customerModel.getIdLogInInf());
            }
            logInInfEntity.setEmail(customerModel.getEmail());
            logInInfEntity.setPassword(customerModel.getPassword());
            logInInfEntity.setRole(RoleEnum.CUSTOMER);
            customerEntity.setLogInInfByLogInInf(logInInfEntity);
            return customerEntity;
        } else {
            return null;
        }
    }

    public static CustomerModel convertFromBackToFront(CustomerEntity customerEntity) {
        if (customerEntity != null) {
            CustomerModel customerModel = new CustomerModel();
            customerModel.setId(customerEntity.getIdCustomer());
            customerModel.setAge(customerEntity.getAge());
            customerModel.setEmail(customerEntity.getLogInInfByLogInInf().getEmail());
            customerModel.setName(customerEntity.getName());
            customerModel.setSurname(customerEntity.getSurname());
            customerModel.setPassword(null);
            customerModel.setWallet(customerEntity.getWalletByIdWallet());
            customerModel.setIsActive(customerEntity.getIsActive());
            customerModel.setIdLogInInf(customerEntity.getLogInInfByLogInInf().getIdLogInInf());
            return customerModel;
        } else {
            return null;
        }
    }
}

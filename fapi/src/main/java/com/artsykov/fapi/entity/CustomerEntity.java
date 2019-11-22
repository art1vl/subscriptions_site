package com.artsykov.fapi.entity;

import lombok.Data;

@Data
public class CustomerEntity {
    private int idCustomer;
    private String name;
    private String surname;
    private int age;
    private byte isActive;
//    private int logInInf;
//    private int idWallet;
    private LogInInfEntity logInInfByLogInInf;
    private WalletEntity walletByIdWallet;
//    private Collection<SubscriptionEntity> subscriptionsByIdCostumer;

}

package com.artsykov.fapi.entity;

import lombok.Data;

@Data
public class CustomerEntity {
    private int idCustomer;
    private String name;
    private String surname;
    private int age;
    private byte isActive;
    private LogInInfEntity logInInf;
    private WalletEntity walletByIdWallet;
}

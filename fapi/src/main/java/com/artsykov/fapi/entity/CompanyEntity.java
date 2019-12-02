package com.artsykov.fapi.entity;

import lombok.Data;

@Data
public class CompanyEntity {
    private int idCompany;
    private String companyName;
    private byte isActive;
    private LogInInfEntity logInInfByLogInInf;
    private WalletEntity walletByIdWallet;
}

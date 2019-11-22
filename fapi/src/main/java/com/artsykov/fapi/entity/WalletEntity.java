package com.artsykov.fapi.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

@Data
public class WalletEntity {
    private int idWallet;
    private int balance;
    private long cardNumber;
    private Timestamp cardDate;
    private int cardCvvCode;
    private String personName;
//    private Collection<CompanyEntity> companiesByIdWallet;
//    private Collection<CustomerEntity> costumersByIdWallet;


    public WalletEntity(int idWallet, int balance, long cardNumber, Timestamp cardDate, int cardCvvCode, String personName) {
        this.idWallet = idWallet;
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.cardDate = cardDate;
        this.cardCvvCode = cardCvvCode;
        this.personName = personName;
    }
}

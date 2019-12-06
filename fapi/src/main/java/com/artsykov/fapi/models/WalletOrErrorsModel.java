package com.artsykov.fapi.models;

import lombok.Data;

import java.util.Map;

@Data
public class WalletOrErrorsModel {
    private WalletModel walletModel;
    private Map<String, String> errors;

    public WalletOrErrorsModel () {}

    public WalletOrErrorsModel(Map<String, String> errors) {
        this.errors = errors;
    }
}

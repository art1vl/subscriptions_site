package com.artsykov.fapi.converter;

import com.artsykov.fapi.entity.WalletEntity;
import com.artsykov.fapi.models.WalletModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WalletConverter {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    public WalletEntity convertFromFrontToBack(WalletModel walletModel) {
        if (walletModel != null) {
            if (walletModel.getIdWallet() != 0) {
                RestTemplate restTemplate = new RestTemplate();
                WalletEntity walletEntity = restTemplate.getForObject(backendServerUrl + "/api/wallet/" +
                        walletModel.getIdWallet(), WalletEntity.class);
                walletEntity.setBalance(walletModel.getBalance());
                walletEntity.setDebt(walletModel.getDebt());
                return walletEntity;
            } else {
                WalletEntity walletEntity = new WalletEntity();
                walletEntity.setBalance(walletModel.getBalance());
                walletEntity.setDebt(walletModel.getDebt());
                walletEntity.setCardNumber(walletModel.getCardNumber());
                walletEntity.setCardDate(walletModel.getCardDate());
                walletEntity.setCardCvvCode(walletModel.getCardCvvCode());
                walletEntity.setPersonName(walletModel.getPersonName());
                return walletEntity;
            }
        } else {
            return null;
        }
    }

    public WalletModel convertFromBackToFront(WalletEntity walletEntity) {
        if (walletEntity != null) {
            WalletModel walletModel = new WalletModel();
            walletModel.setBalance(walletEntity.getBalance());
            walletModel.setIdWallet(walletEntity.getIdWallet());
            walletModel.setDebt(walletEntity.getDebt());
            walletModel.setCardCvvCode(0);
            walletModel.setCardDate(walletEntity.getCardDate());
            walletModel.setCardNumber(walletEntity.getCardNumber());
            walletModel.setPersonName("");
            return walletModel;
        } else {
            return null;
        }
    }
}
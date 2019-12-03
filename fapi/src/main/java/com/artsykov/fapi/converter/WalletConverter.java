package com.artsykov.fapi.converter;

import com.artsykov.fapi.entity.WalletEntity;
import com.artsykov.fapi.models.WalletModel;
import org.springframework.stereotype.Component;

@Component
public class WalletConverter {
    public WalletEntity convertFromFrontToBack(WalletModel walletModel) {
        if (walletModel != null) {
            WalletEntity walletEntity = new WalletEntity();
            if (walletModel.getIdWallet() != 0) {
                walletEntity.setIdWallet(walletModel.getIdWallet());
            }
            walletEntity.setBalance(walletModel.getBalance());
            walletEntity.setCardNumber(walletModel.getCardNumber());
            walletEntity.setCardDate(walletModel.getCardDate());
            walletEntity.setCardCvvCode(walletModel.getCardCvvCode());
            walletEntity.setPersonName(walletModel.getPersonName());
            return walletEntity;
        }
        else {
            return null;
        }
    }

    public WalletModel convertFromBackToFront(WalletEntity walletEntity) {
        if (walletEntity != null) {
            WalletModel walletModel = new WalletModel();
            walletModel.setBalance(walletEntity.getBalance());
            walletModel.setIdWallet(walletEntity.getIdWallet());
            walletModel.setCardCvvCode(0);
            walletModel.setCardDate(walletEntity.getCardDate());
            walletModel.setCardNumber(walletEntity.getCardNumber());
            walletModel.setPersonName("");
            return walletModel;
        }
        else {
            return null;
        }
    }
}
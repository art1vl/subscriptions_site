package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.WalletConverter;
import com.artsykov.fapi.entity.WalletEntity;
import com.artsykov.fapi.models.WalletModel;
import com.artsykov.fapi.service.WalletDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WalletDataServiceImpl implements WalletDataService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private WalletConverter walletConverter;

    @Override
    public void deleteWallet(int id) {
        RestTemplate restTemplate = new RestTemplate();
        WalletEntity walletEntity = restTemplate.getForEntity(
                backendServerUrl + "/api/wallet/" + id, WalletEntity.class).getBody();
        walletEntity.setPersonName(null);
        walletEntity.setCardCvvCode(0);
        walletEntity.setCardNumber(0);
        walletEntity.setCardDate(null);
        restTemplate.postForEntity(backendServerUrl + "/api/wallet", walletEntity, WalletEntity.class);
    }

    @Override
    public void updateWallet(WalletModel walletModel) {
        RestTemplate restTemplate = new RestTemplate();
        WalletEntity walletEntity = walletConverter.convertFromFrontToBack(walletModel);
        restTemplate.put(backendServerUrl + "/api/wallet", walletEntity);
    }

    @Override
    public WalletModel findWalletById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return walletConverter.convertFromBackToFront(restTemplate.getForObject(
                backendServerUrl + "/api/wallet/" + id, WalletEntity.class));
    }
}

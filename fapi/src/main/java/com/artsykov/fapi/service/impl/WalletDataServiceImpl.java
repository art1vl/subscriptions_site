package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.entity.WalletEntity;
import com.artsykov.fapi.service.WalletDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WalletDataServiceImpl implements WalletDataService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public void deleteWallet(int id) {
        RestTemplate restTemplate = new RestTemplate();
        WalletEntity walletEntity = restTemplate.getForEntity(backendServerUrl + "/api/wallet/" + id, WalletEntity.class).getBody();
        walletEntity.setPersonName(null);
        walletEntity.setCardCvvCode(0);
        walletEntity.setCardNumber(0);
        walletEntity.setCardDate(null);
        restTemplate.postForEntity(backendServerUrl + "/api/wallet", walletEntity, WalletEntity.class);
    }
}

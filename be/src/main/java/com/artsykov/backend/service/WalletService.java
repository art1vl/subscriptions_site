package com.artsykov.backend.service;

import com.artsykov.backend.entity.WalletEntity;

public interface WalletService {
    WalletEntity save(WalletEntity walletEntity);

    WalletEntity find(int id);

    WalletEntity update(WalletEntity walletEntity);

    WalletEntity findWalletByIdCustomer(int customerId);

    WalletEntity findWalletByIdCompany(int companyId);
}

package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.WalletEntity;
import com.artsykov.backend.repository.WalletRepository;
import com.artsykov.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletRepository walletRepository;

    @Override
    public WalletEntity find(int id) {
        return walletRepository.findByIdWallet(id);
    }

    @Override
    public WalletEntity save(WalletEntity walletEntity) {
        return walletRepository.save(walletEntity);
    }

    @Override
    public WalletEntity update(WalletEntity walletEntity) {
        return walletRepository.save(walletEntity);
    }

    @Override
    public WalletEntity findWalletByIdCustomer(int customerId) {
        return walletRepository.findWalletEntityByIdCustomer(customerId);
    }

    @Override
    public WalletEntity findWalletByIdCompany(int companyId) {
        return walletRepository.findWalletEntityByIdCompany(companyId);
    }
}

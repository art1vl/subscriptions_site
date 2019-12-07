package com.artsykov.fapi.service;

import com.artsykov.fapi.models.WalletModel;

public interface WalletDataService {
    void deleteWallet (int id);

    void replenish (WalletModel walletModel);
}

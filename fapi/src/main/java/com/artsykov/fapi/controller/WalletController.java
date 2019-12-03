package com.artsykov.fapi.controller;

import com.artsykov.fapi.service.WalletDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/wallet")
public class WalletController {
    @Autowired
    private WalletDataService walletDataService;

    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteWallet (@PathVariable("id") Integer id) {
        walletDataService.deleteWallet(id);
        return HttpStatus.OK;
    }
}

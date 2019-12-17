package com.artsykov.backend.controller;

import com.artsykov.backend.entity.WalletEntity;
import com.artsykov.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/wallet")
public class WalletController {
    @Autowired
    WalletService walletService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<WalletEntity> getWallet(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(walletService.find(id));
    }

    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<WalletEntity> getWalletByCustomerId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(walletService.findWalletByIdCustomer(id));
    }

    @GetMapping(value = "/company/{id}")
    public ResponseEntity<WalletEntity> getWalletByCompanyId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(walletService.findWalletByIdCompany(id));
    }

    @PostMapping
    public ResponseEntity<WalletEntity> saveWallet(@RequestBody WalletEntity walletEntity) {
        return ResponseEntity.ok(walletService.save(walletEntity));
    }

    @PutMapping
    public HttpStatus updateWallet (@RequestBody WalletEntity walletEntity) {
        walletService.update(walletEntity);
        return HttpStatus.OK;
    }
}

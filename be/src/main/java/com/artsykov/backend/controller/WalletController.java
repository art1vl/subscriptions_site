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
    public ResponseEntity<WalletEntity> getWallet(@PathVariable("id") String id) {
        int intId = Integer.valueOf(id);
        return ResponseEntity.ok(walletService.find(intId));
    }

    @PostMapping
    public ResponseEntity<WalletEntity> saveWallet(@RequestBody WalletEntity walletEntity) {
        return ResponseEntity.ok(walletService.save(walletEntity));
    }

    @PutMapping
    public HttpStatus replenish (@RequestBody WalletEntity walletEntity) {
        walletService.replenish(walletEntity);
        return HttpStatus.OK;
    }
}

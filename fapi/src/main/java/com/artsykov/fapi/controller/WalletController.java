package com.artsykov.fapi.controller;

import com.artsykov.fapi.controller.handler.HandlerService;
import com.artsykov.fapi.models.WalletModel;
import com.artsykov.fapi.models.WalletOrErrorsModel;
import com.artsykov.fapi.service.WalletDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/wallet")
public class WalletController {

    @Autowired
    private WalletDataService walletDataService;

    @Autowired
    private HandlerService handlerService;

    @Secured({"ROLE_COMPANY", "ROLE_CUSTOMER"})
    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteWallet (@PathVariable("id") Integer id) {
        walletDataService.deleteWallet(id);
        return HttpStatus.OK;
    }

    @Secured({"ROLE_COMPANY", "ROLE_CUSTOMER"})
    @PutMapping(value = "/update")
    public ResponseEntity<WalletOrErrorsModel> updateWallet (@RequestBody WalletModel walletModel) {
        walletDataService.updateWallet(walletModel);
        return ResponseEntity.ok(new WalletOrErrorsModel());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WalletOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(new WalletOrErrorsModel(handlerService.handleMethodArgumentNotValid(ex)));
    }
}

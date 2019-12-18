package com.artsykov.fapi.controller;

import com.artsykov.fapi.controller.handler.HandlerService;
import com.artsykov.fapi.models.*;
import com.artsykov.fapi.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    private SubscriptionService subscriptionService;
    private HandlerService handlerService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService,
                                  HandlerService handlerService) {
        this.subscriptionService = subscriptionService;
        this.handlerService = handlerService;
    }

    @Secured("ROLE_CUSTOMER")
    @GetMapping
    public ResponseEntity<SubscriptionModel> isCustomerSubscribed(@RequestParam("product") Integer productId,
                                                                  @RequestParam("customer") Integer customerId) {
        return ResponseEntity.ok(subscriptionService.findSubscription(productId, customerId));
    }

    @Secured("ROLE_CUSTOMER")
    @PostMapping
    public ResponseEntity<SubscriptionOrErrorsModel> saveSubscription(@RequestBody @Valid SubscriptionModel subscriptionModel) {
        return ResponseEntity.ok(subscriptionService.saveSubscription(subscriptionModel));
    }

    @Secured("ROLE_CUSTOMER")
    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<CustomerSubscriptionPageModel> findAllSubscriptionsByCustomerId(@PathVariable Integer id,
                                                                                          @RequestParam("page") Integer pageNumber,
                                                                                          @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(subscriptionService.findAllSubscriptionsByCustomerId(id, pageNumber, amount));
    }

    @Secured("ROLE_CUSTOMER")
    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteById(@PathVariable("id") Integer id) {
        subscriptionService.deleteById(id);
        return HttpStatus.OK;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SubscriptionOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(new SubscriptionOrErrorsModel(handlerService.handleMethodArgumentNotValid(ex)));
    }
}

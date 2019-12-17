package com.artsykov.backend.controller;

import com.artsykov.backend.entity.SubscriptionEntity;
import com.artsykov.backend.model.CustomerSubscriptionPageModel;
import com.artsykov.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<SubscriptionEntity> findSubscription(@RequestParam("product") Integer productId,
                                                               @RequestParam("customer") Integer customerId) {
        return ResponseEntity.ok(subscriptionService.findSubscription(productId, customerId));
    }

    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<CustomerSubscriptionPageModel> findAllByCustomerId(@RequestParam("page") Integer page,
                                                                             @RequestParam("amount") Integer amount,
                                                                             @PathVariable("id") Integer id) {
        return ResponseEntity.ok(subscriptionService.findAllByCustomerId(id, page, amount));
    }

    @PostMapping
    public ResponseEntity<SubscriptionEntity> saveSubscription(@RequestBody SubscriptionEntity subscriptionEntity) {
        return ResponseEntity.ok(subscriptionService.save(subscriptionEntity));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        subscriptionService.deleteById(id);
    }
}

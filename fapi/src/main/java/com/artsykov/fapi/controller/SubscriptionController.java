package com.artsykov.fapi.controller;

import com.artsykov.fapi.models.*;
import com.artsykov.fapi.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @Secured("ROLE_CUSTOMER")
    @GetMapping
    public ResponseEntity<SubscriptionModel> isCustomerSubscribed(@RequestParam("product") Integer productId,
                                                        @RequestParam("customer") Integer customerId) {
        return ResponseEntity.ok(subscriptionService.findSubscription(productId, customerId));
    }

    @Secured("ROLE_CUSTOMER")
    @PostMapping
    public ResponseEntity<SubscriptionOrErrorsModel> saveSubscription(@RequestBody SubscriptionModel subscriptionModel) {
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

    //todo do I need it????npm
    @RequestMapping
    public ResponseEntity<List<SubscriptionModel>> findAllSubscriptions() throws InterruptedException {
        ProductModel product = new ProductModel(1, 1, "Microsoft Word is a widely used commercial word processor designed by Microsoft. Microsoft Word is a component of the Microsoft Office suite of productivity software, but can also be purchased as a stand-alone product.\n" +
                "It was initially launched in 1983 and has since been revised numerous times. Microsoft Word is available for both Windows and Macintosh operating systems.\n" +
                "Microsoft Word is often called simply Word or MS Word.", "../../../../../assets/footer-photos/Microsoft_Word_2013_logo.svg_.png", new ProductTypeModel(1, "business"), new Date(1570000000000L), 10, "Microsoft word 2013", 1);
        SubscriptionModel subscriptionModel = new SubscriptionModel(1, product, 1, new Date(1590000000000L), 1);
        List<SubscriptionModel> list = new ArrayList<>();
        list.add(subscriptionModel);
        list.add(subscriptionModel);
        return ResponseEntity.ok(list);
    }
}

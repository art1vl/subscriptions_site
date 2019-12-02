package com.artsykov.fapi.controller;

import com.artsykov.fapi.models.SubscriptionModel;
import com.artsykov.fapi.models.ProductModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @RequestMapping(value = "/{id}")
    public ResponseEntity<List<SubscriptionModel>> findAllSubscriptionsByCustomerId(@PathVariable String id) throws InterruptedException {
//        Long billingAccountId = Long.valueOf(id);
//        return ResponseEntity.ok(billingAccountDataService.getBillingAccountById(billingAccountId));
        ProductModel product = new ProductModel(1, "Microsoft", "Microsoft Word is a widely used commercial word processor designed by Microsoft. Microsoft Word is a component of the Microsoft Office suite of productivity software, but can also be purchased as a stand-alone product.\n" +
                "It was initially launched in 1983 and has since been revised numerous times. Microsoft Word is available for both Windows and Macintosh operating systems.\n" +
                "Microsoft Word is often called simply Word or MS Word.", "../../../../../assets/footer-photos/Microsoft_Word_2013_logo.svg_.png", "business", new Timestamp(1570000000000L), 10, "Microsoft word 2013");
        SubscriptionModel subscriptionModel = new SubscriptionModel(1, product, 1, new Timestamp(1590000000000L), true);
        List<SubscriptionModel> list = new ArrayList<>();
        list.add(subscriptionModel);
        list.add(subscriptionModel);
        return ResponseEntity.ok(list);
    }

    @RequestMapping
    public ResponseEntity<List<SubscriptionModel>> findAllSubscriptions() throws InterruptedException {
        ProductModel product = new ProductModel(1, "Microsoft", "Microsoft Word is a widely used commercial word processor designed by Microsoft. Microsoft Word is a component of the Microsoft Office suite of productivity software, but can also be purchased as a stand-alone product.\n" +
                "It was initially launched in 1983 and has since been revised numerous times. Microsoft Word is available for both Windows and Macintosh operating systems.\n" +
                "Microsoft Word is often called simply Word or MS Word.", "../../../../../assets/footer-photos/Microsoft_Word_2013_logo.svg_.png", "business", new Timestamp(1570000000000L), 10, "Microsoft word 2013");
        SubscriptionModel subscriptionModel = new SubscriptionModel(1, product, 1, new Timestamp(1590000000000L), true);
        List<SubscriptionModel> list = new ArrayList<>();
        list.add(subscriptionModel);
        list.add(subscriptionModel);
        return ResponseEntity.ok(list);
    }
}

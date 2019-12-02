package com.artsykov.fapi.controller;

import com.artsykov.fapi.models.ProductModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductDataController {

    @RequestMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        ProductModel product = new ProductModel(1, "Microsoft", "Mttfyftyfyfyftyn  dr dy dry dr drt drt dtdtdd dr", "../../../../../assets/footer-photos/Microsoft_Word_2013_logo.svg_.png", "business", new Timestamp(1570000000000L), 10, "Microsoft word 2013");
        ProductModel product1 = new ProductModel(2, "Microsoft", "Microsoft Excel 2020", "../../../../../assets/footer-photos/s428500189780081722_p1_i7_w500.gif",  "business", new Timestamp(1570000000000L), 10, "Microsoft word 2013");
        List<ProductModel> list = new ArrayList<>();
        list.add(product);
        list.add(product1);
        list.add(product1);
        list.add(product1);
        list.add(product1);
        list.add(product1);
        list.add(product1);
        list.add(product1);
        return ResponseEntity.ok(list);
        //return ResponseEntity.ok(billingAccountDataService.getAll());
    }

    @RequestMapping(value = "/types")
    public ResponseEntity<String[]> getAllTypes() {
        String[] types = new String[3];
        types[0] = "business";
        types[1] = "home";
        types[2] = "music";
        return ResponseEntity.ok(types);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable String id) throws InterruptedException {
//        Long billingAccountId = Long.valueOf(id);
//        return ResponseEntity.ok(billingAccountDataService.getBillingAccountById(billingAccountId));
        ProductModel product = new ProductModel(1, "Microsoft", "Microsoft Word is a widely used commercial word processor designed by Microsoft. Microsoft Word is a component of the Microsoft Office suite of productivity software, but can also be purchased as a stand-alone product.\n" +
                "It was initially launched in 1983 and has since been revised numerous times. Microsoft Word is available for both Windows and Macintosh operating systems.\n" +
                "Microsoft Word is often called simply Word or MS Word.", "../../../../../assets/footer-photos/Microsoft_Word_2013_logo.svg_.png", "business", new Timestamp(1570000000000L), 10, "Microsoft word 2013");
        return ResponseEntity.ok(product);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProductModel> saveBillingAccount(@RequestBody ProductModel billingAccount /*todo server validation*/) {
//        if (billingAccount != null) {
//            return ResponseEntity.ok(billingAccountDataService.saveBillingAccount(billingAccount));
//        }
        return null;
    }

    @RequestMapping(value = "/{id}/{id}", method = RequestMethod.DELETE)
    public void deleteBillingAccount(@PathVariable String subscriptionId, String customerId) {
       // billingAccountDataService.deleteBillingAccount(Long.valueOf(id));
    }
}

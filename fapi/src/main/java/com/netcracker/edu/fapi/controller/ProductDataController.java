package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductDataController {

    @RequestMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        ProductModel product = new ProductModel(1, "Microsoft", "Mttfyftyfyfyftyn  dr dy dry dr drt drt dtdtdd dr", "../../../../../assets/footer-photos/Microsoft_Word_2013_logo.svg_.png");
        ProductModel product1 = new ProductModel(1, "Microsoft", "Microsoft Excel 2020", "../../../../../assets/footer-photos/s428500189780081722_p1_i7_w500.gif");
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProductModel> saveBillingAccount(@RequestBody ProductModel billingAccount /*todo server validation*/) {
//        if (billingAccount != null) {
//            return ResponseEntity.ok(billingAccountDataService.saveBillingAccount(billingAccount));
//        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBillingAccount(@PathVariable String id) {
       // billingAccountDataService.deleteBillingAccount(Long.valueOf(id));
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<ProductModel> getAllBillingAccounts(@PathVariable String id) throws InterruptedException {
//        Long billingAccountId = Long.valueOf(id);
//        return ResponseEntity.ok(billingAccountDataService.getBillingAccountById(billingAccountId));
        return null;
    }

}

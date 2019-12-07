package com.artsykov.fapi.controller;

import com.artsykov.fapi.controller.handler.HandlerService;
import com.artsykov.fapi.entity.ProductTypeEntity;
import com.artsykov.fapi.models.*;
import com.artsykov.fapi.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductDataService productDataService;

    @Autowired
    private HandlerService handlerService;

    //todo
    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        ProductModel product = new ProductModel(1, 1, "Mttfyftyfyfyftyn  dr dy dry dr drt drt dtdtdd dr", "file:/C:/my_files/photos/iris.jpg", new ProductTypeModel(1, "business"), new Date(1570000000000L), 10, "Microsoft word 2013", 1);
        ProductModel product1 = new ProductModel(2, 1, "Microsoft Excel 2020", "../../../../../assets/footer-photos/s428500189780081722_p1_i7_w500.gif",  new ProductTypeModel(1, "business"), new Date(1570000000000L), 10, "Microsoft word 2013", 1);
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
    }

    //todo
    @RequestMapping(value = "/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable String id) throws InterruptedException {
//        Long billingAccountId = Long.valueOf(id);
//        return ResponseEntity.ok(billingAccountDataService.getBillingAccountById(billingAccountId));
        ProductModel product = new ProductModel(1, 1, "Microsoft Word is a widely used commercial word processor designed by Microsoft. Microsoft Word is a component of the Microsoft Office suite of productivity software, but can also be purchased as a stand-alone product.\n" +
                "It was initially launched in 1983 and has since been revised numerous times. Microsoft Word is available for both Windows and Macintosh operating systems.\n" +
                "Microsoft Word is often called simply Word or MS Word.", "../../../../../assets/footer-photos/Microsoft_Word_2013_logo.svg_.png", new ProductTypeModel(1, "business"), new Date(1570000000000L), 10, "Microsoft word 2013", 1);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductOrErrorsModel> saveProduct(@RequestBody @Valid ProductModel productModel) {
        return ResponseEntity.ok(new ProductOrErrorsModel(productDataService.saveProduct(productModel)));
    }

    @PostMapping(value = "/{id}/image")
    public ResponseEntity<ProductOrErrorsModel> saveProductImage(@PathVariable("id") Integer id, @RequestParam(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.ok(productDataService.saveProductImage(id, file));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProductOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(new ProductOrErrorsModel(handlerService.handleMethodArgumentNotValid(ex)));
    }
}

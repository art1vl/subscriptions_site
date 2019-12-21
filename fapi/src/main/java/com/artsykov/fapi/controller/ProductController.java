package com.artsykov.fapi.controller;

import com.artsykov.fapi.controller.handler.HandlerService;
import com.artsykov.fapi.models.ProductModel;
import com.artsykov.fapi.models.ProductOrErrorsModel;
import com.artsykov.fapi.models.ProductPageModel;
import com.artsykov.fapi.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductDataService productDataService;
    private HandlerService handlerService;

    @Autowired
    public ProductController(ProductDataService productDataService,
                             HandlerService handlerService) {
        this.productDataService = productDataService;
        this.handlerService = handlerService;
    }

    @GetMapping
    public ResponseEntity<ProductPageModel> getProductsByPageIsActive(@RequestParam("page") Integer pageNumber,
                                                                      @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(productDataService.findProductsByPageIsActive(pageNumber, amount));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/all")
    public ResponseEntity<ProductPageModel> getAllByPфge(@RequestParam("page") Integer pageNumber,
                                                         @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(productDataService.findAllByPage(pageNumber, amount));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable Integer id) throws InterruptedException {
        return ResponseEntity.ok(productDataService.findProductById(id));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<ProductPageModel> getProductBySearchByPage(@RequestParam(value = "product", required = false) String productName,
                                                                     @RequestParam(value = "company", required = false) String companyName,
                                                                     @RequestParam(value = "min", required = false) String min,
                                                                     @RequestParam(value = "max", required = false) String max,
                                                                     @RequestParam(value = "type", required = false) String productType,
                                                                     @RequestParam(value = "page") Integer pageNumber,
                                                                     @RequestParam(value = "amount") Integer amount) {
        return ResponseEntity.ok(productDataService.findAllProductsBySearchByPage(productName, companyName, min, max, productType,
                pageNumber, amount));
    }

    @Secured("ROLE_COMPANY")
    @GetMapping(value = "/company/{id}")
    public ResponseEntity<ProductPageModel> getProductsByPageByCompanyId(@PathVariable("id") Integer companyId,
                                                                         @RequestParam("page") Integer pageNumber,
                                                                         @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(productDataService.findProductsByPageByCompanyId(companyId, pageNumber, amount));
    }

    @Secured("ROLE_COMPANY")
    @PostMapping
    public ResponseEntity<ProductOrErrorsModel> saveProduct(@RequestBody @Valid ProductModel productModel) {
        return ResponseEntity.ok(new ProductOrErrorsModel(productDataService.saveProduct(productModel)));
    }

    @Secured("ROLE_COMPANY")
    @PostMapping(value = "/{id}/image")
    public ResponseEntity<ProductOrErrorsModel> saveProductImage(@PathVariable("id") Integer id,
                                                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.ok(productDataService.saveProductImage(id, file));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<ProductOrErrorsModel> changeProductStatus(@RequestBody @Valid ProductModel productModel) {
        productDataService.changeProductStatus(productModel);
        return ResponseEntity.ok(new ProductOrErrorsModel(productModel));
    }

    @Secured("ROLE_COMPANY")
    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteProductById(@PathVariable("id") Integer productId) {
        productDataService.deleteProductById(productId);
        return HttpStatus.OK;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProductOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(new ProductOrErrorsModel(handlerService.handleMethodArgumentNotValid(ex)));
    }
}

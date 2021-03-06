package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.CompanyEntity;
import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.entity.ProductTypeEntity;
import com.artsykov.backend.model.ProductPageModel;
import com.artsykov.backend.repository.CompanyRepository;
import com.artsykov.backend.repository.ProductRepository;
import com.artsykov.backend.repository.ProductTypeRepository;
import com.artsykov.backend.service.ProductService;
import com.artsykov.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CompanyRepository companyRepository;
    private SubscriptionService subscriptionService;
    private ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              CompanyRepository companyRepository,
                              SubscriptionService subscriptionService,
                              ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
        this.subscriptionService = subscriptionService;
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity findProduct(int id) {
        return productRepository.findByIdProduct(id);
    }

    @Override
    public ProductPageModel findByPageIsActive(int pageNumber, int amount) {
        Pageable pageable = PageRequest.of(pageNumber, amount);
        Page<ProductEntity> page = productRepository.findAllByIsActive((byte) 1, pageable);
        return createProductPageModel(page);
    }

    @Override
    public ProductPageModel findByPageByCompanyId(int companyId, int pageNumber, int amount) {
        Pageable pageable = PageRequest.of(pageNumber, amount);
        CompanyEntity companyEntity = companyRepository.findByIdCompany(companyId);
        Page<ProductEntity> page = productRepository.findAllByCompany(companyEntity, pageable);
        return createProductPageModel(page);
    }

    @Override
    public ProductPageModel findAllByPage(int pageNumber, int amount) {
        Pageable pageable = PageRequest.of(pageNumber, amount);
        Page<ProductEntity> page = productRepository.findAll(pageable);
        return createProductPageModel(page);
    }

    @Override
    public void deleteProductById(int productId) {
        productRepository.deleteByIdProduct(productId);
    }

    @Override
    public void changeProductStatus(ProductEntity productEntity) {
        productRepository.save(productEntity);
        subscriptionService.changeSubscriptionStatusByProduct(productEntity);
    }

    @Override
    public List<ProductEntity> findAllByCompanyEntity(CompanyEntity companyEntity) {
        return productRepository.findAllByCompany(companyEntity);
    }

    @Override
    public ProductPageModel findAllProductsBySearchByPage(String productName, String companyName, Integer min, Integer max,
                                                          String productType, int pageNumber, int amount) {
        Pageable pageable = PageRequest.of(pageNumber, amount);
//        CompanyEntity companyEntity = companyRepository.findByCompanyName(companyName);
//        ProductTypeEntity productTypeEntity = productTypeRepository.findByIdProductType(productTypeId);
        Page<ProductEntity> page = productRepository.findAllBySearchByPage(productName, companyName, min, max,
                                                                            productType, pageable);
        return createProductPageModel(page);
    }

    private ProductPageModel createProductPageModel(Page<ProductEntity> page) {
        ProductPageModel productPageModel = new ProductPageModel();
        productPageModel.setProductList(page.getContent());
        productPageModel.setTotalPages(page.getTotalPages());
        productPageModel.setTotalElements(page.getTotalElements());
        return productPageModel;
    }
}

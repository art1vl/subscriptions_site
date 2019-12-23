package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.CompanyEntity;
import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.model.CompanyPageModel;
import com.artsykov.backend.repository.CompanyRepository;
import com.artsykov.backend.service.CompanyService;
import com.artsykov.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private ProductService productService;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository,
                              ProductService productService) {
        this.companyRepository = companyRepository;
        this.productService = productService;
    }

    @Override
    public CompanyEntity findCompany(int companyId) {
        return companyRepository.findByIdCompany(companyId);
    }

    @Override
    public CompanyEntity saveWallet(CompanyEntity companyEntity) {
        return companyRepository.save(companyEntity);
    }

    @Override
    public CompanyEntity findCompanyByLogInInfId(int logInInfId) {
        return companyRepository.findByLogInInfIdLogInInf(logInInfId);
    }

    @Override
    public CompanyEntity saveCompany(CompanyEntity companyEntity) {
        return companyRepository.save(companyEntity);
    }

    @Override
    public CompanyPageModel findAllByPage(int pageNumber, int amount) {
        Pageable pageable = PageRequest.of(pageNumber, amount);
        Page<CompanyEntity> page = companyRepository.findAll(pageable);
        CompanyPageModel companyPageModel = new CompanyPageModel();
        companyPageModel.setCompanyEntityList(page.getContent());
        companyPageModel.setTotalPages(page.getTotalPages());
        companyPageModel.setTotalElements(page.getTotalElements());
        return companyPageModel;
    }

    @Override
    public void changeStatus(CompanyEntity companyEntity) {
        companyRepository.save(companyEntity);
        List<ProductEntity> productEntityList = productService.findAllByCompanyEntity(companyEntity);
        for (ProductEntity product : productEntityList) {
            product.setIsActive(companyEntity.getIsActive());
            productService.changeProductStatus(product);
        }
    }
}

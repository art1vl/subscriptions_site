package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.CompanyEntity;
import com.artsykov.backend.repository.CompanyRepository;
import com.artsykov.backend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyEntity saveCompany(CompanyEntity companyEntity) {
        return companyRepository.save(companyEntity);

    }
}

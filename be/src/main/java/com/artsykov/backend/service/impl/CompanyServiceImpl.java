package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.CompanyEntity;
import com.artsykov.backend.repository.CompanyRepository;
import com.artsykov.backend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

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
}

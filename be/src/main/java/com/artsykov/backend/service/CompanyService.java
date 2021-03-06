package com.artsykov.backend.service;

import com.artsykov.backend.entity.CompanyEntity;
import com.artsykov.backend.model.CompanyPageModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CompanyService {
    CompanyEntity saveCompany(CompanyEntity companyEntity);

    CompanyEntity findCompanyByLogInInfId(int logInInfId);

    CompanyEntity findCompany(int companyId);

    CompanyEntity saveWallet(CompanyEntity companyEntity);

    CompanyPageModel findAllByPage(int pageNumber, int amount);

    void changeStatus(CompanyEntity companyEntity);
}

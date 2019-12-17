package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.CompanyConverter;
import com.artsykov.fapi.converter.WalletConverter;
import com.artsykov.fapi.entity.CompanyEntity;
import com.artsykov.fapi.entity.WalletEntity;
import com.artsykov.fapi.models.CompanyModel;
import com.artsykov.fapi.models.CompanyOrErrorsModel;
import com.artsykov.fapi.models.WalletModel;
import com.artsykov.fapi.service.CompanyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CompanyDataServiceImpl implements CompanyDataService {
    private CompanyConverter companyConverter;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private WalletConverter walletConverter;

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    public CompanyDataServiceImpl(CompanyConverter companyConverter,
                                  BCryptPasswordEncoder bCryptPasswordEncoder,
                                  WalletConverter walletConverter) {
        this.companyConverter = companyConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.walletConverter = walletConverter;
    }

    @Override
    public CompanyModel getCompanyByLogInInfId(int logInInfId) {
        RestTemplate restTemplate = new RestTemplate();
        return companyConverter.convertFromBackToFront(restTemplate.getForObject(backendServerUrl +
                "/api/company/log/in/inf/" + logInInfId, CompanyEntity.class));
    }

    @Override
    public CompanyModel findCompanyById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return companyConverter.convertFromBackToFront(restTemplate.getForObject(backendServerUrl +
                "/api/company/" + id, CompanyEntity.class));
    }

    @Override
    public CompanyOrErrorsModel saveCompany(CompanyModel company) {
        CompanyOrErrorsModel companyOrErrorsModel = new CompanyOrErrorsModel();
        RestTemplate restTemplate = new RestTemplate();
        boolean existsEmail = restTemplate.getForObject(backendServerUrl + "/api/log/in/inf/exist/" +
                         company.getEmail(), Boolean.class);
        if (existsEmail) {
            Map<String, String> errors = new HashMap<>();
            errors.put("email", "This email is busy");
            companyOrErrorsModel.setErrors(errors);
        }
        else {
            company.setPassword(bCryptPasswordEncoder.encode(company.getPassword()));
            CompanyEntity companyEntity = companyConverter.convertFromFrontToBack(company);
            companyOrErrorsModel.setCompanyModel(companyConverter.convertFromBackToFront(restTemplate
                    .postForEntity(backendServerUrl + "/api/company",
                    companyEntity, CompanyEntity.class).getBody()));
        }
        return companyOrErrorsModel;
    }

    @Override
    public CompanyModel saveWallet(CompanyModel companyModel) {
        RestTemplate restTemplate = new RestTemplate();
        CompanyEntity companyEntity = companyConverter.convertFromFrontToBack(companyModel);
        WalletEntity walletEntity = companyEntity.getWalletByIdWallet();
        walletEntity = restTemplate.postForEntity(backendServerUrl + "/api/wallet", walletEntity, WalletEntity.class).getBody();
        companyEntity.setWalletByIdWallet(walletEntity);
        return companyConverter.convertFromBackToFront(restTemplate.postForEntity(backendServerUrl + "/api/company/wallet", companyEntity, CompanyEntity.class).getBody());
    }

    @Override
    public WalletModel findWalletByCompanyId(int companyId) {
        RestTemplate restTemplate = new RestTemplate();
        return walletConverter.convertFromBackToFront(restTemplate.getForObject(backendServerUrl +
                "/api/wallet/company/" + companyId, WalletEntity.class));
    }
}

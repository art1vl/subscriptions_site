package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.CompanyConverter;
import com.artsykov.fapi.entity.CompanyEntity;
import com.artsykov.fapi.entity.CustomerEntity;
import com.artsykov.fapi.entity.WalletEntity;
import com.artsykov.fapi.models.CompanyModel;
import com.artsykov.fapi.models.CompanyOrErrorsModel;
import com.artsykov.fapi.service.CompanyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompanyDataServiceImpl implements CompanyDataService {
    private CompanyConverter companyConverter;
  //  private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    public CompanyDataServiceImpl(CompanyConverter companyConverter) {
        this.companyConverter = companyConverter;
       // this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public CompanyModel saveCompany(CompanyModel company) {
        RestTemplate restTemplate = new RestTemplate();
      //  company.setPassword(bCryptPasswordEncoder.encode(company.getPassword()));
        CompanyEntity companyEntity = companyConverter.convertFromFrontToBack(company);
        return companyConverter.convertFromBackToFront(restTemplate.postForEntity(backendServerUrl + "/api/company",
                companyEntity, CompanyEntity.class).getBody());
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
}

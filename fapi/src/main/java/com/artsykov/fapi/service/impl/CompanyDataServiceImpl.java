package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.CompanyConverter;
import com.artsykov.fapi.entity.CompanyEntity;
import com.artsykov.fapi.models.CompanyModel;
import com.artsykov.fapi.models.CompanyOrErrorsModel;
import com.artsykov.fapi.service.CompanyDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompanyDataServiceImpl implements CompanyDataService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public CompanyOrErrorsModel saveCompany(CompanyModel company) {
        CompanyOrErrorsModel companyOrErrorsModel = new CompanyOrErrorsModel();
        RestTemplate restTemplate = new RestTemplate();
        CompanyEntity companyEntity = CompanyConverter.convertFromFrontToBack(company);
        companyOrErrorsModel.setCompanyModel(CompanyConverter.convertFromBackToFront(restTemplate.postForEntity(backendServerUrl + "/api/company",
                companyEntity, CompanyEntity.class).getBody()));
        return companyOrErrorsModel;
    }
}

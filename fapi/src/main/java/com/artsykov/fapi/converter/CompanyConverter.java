package com.artsykov.fapi.converter;

import com.artsykov.fapi.entity.CompanyEntity;
import com.artsykov.fapi.entity.CustomerEntity;
import com.artsykov.fapi.entity.LogInInfEntity;
import com.artsykov.fapi.entity.RoleEnum;
import com.artsykov.fapi.models.CompanyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CompanyConverter {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    private WalletConverter walletConverter;

    @Autowired
    public CompanyConverter(WalletConverter walletConverter) {
        this.walletConverter = walletConverter;
    }

    public CompanyEntity convertFromFrontToBack(CompanyModel companyModel) {
        if (companyModel != null) {
            CompanyEntity companyEntity = new CompanyEntity();
            RestTemplate restTemplate = new RestTemplate();
            CompanyEntity companyEntity1 = restTemplate.getForObject(backendServerUrl + "/api/company/" +
                    companyModel.getId(), CompanyEntity.class);
            if (companyModel.getId() != 0) {
                companyEntity.setIdCompany(companyModel.getId());
            }
            companyEntity.setCompanyName(companyModel.getName());
            companyEntity.setWalletByIdWallet(walletConverter.convertFromFrontToBack(companyModel.getWallet()));
            if (companyModel.getIdLogInInf() != 0) {
                companyEntity.setLogInInf(companyEntity1.getLogInInf());
            }
            else {
                LogInInfEntity logInInfEntity = new LogInInfEntity();
                logInInfEntity.setEmail(companyModel.getEmail());
                logInInfEntity.setPassword(companyModel.getPassword());
                logInInfEntity.setRole(RoleEnum.COMPANY);
                companyEntity.setLogInInf(logInInfEntity);
            }
            companyEntity.setIsActive((byte) companyModel.getIsActive());
            return companyEntity;
        }
        return null;

    }

    public CompanyModel convertFromBackToFront(CompanyEntity companyEntity) {
        if (companyEntity != null) {
            CompanyModel companyModel = new CompanyModel();
            companyModel.setId(companyEntity.getIdCompany());
            companyModel.setEmail(companyEntity.getLogInInf().getEmail());
            companyModel.setName(companyEntity.getCompanyName());
            companyModel.setPassword(null);
            companyModel.setWallet(walletConverter.convertFromBackToFront(companyEntity.getWalletByIdWallet()));
            companyModel.setIdLogInInf(companyEntity.getLogInInf().getIdLogInInf());
            companyModel.setIsActive(companyEntity.getIsActive());
            return companyModel;
        }
        return null;
    }
}

package com.artsykov.fapi.converter;

import com.artsykov.fapi.entity.CompanyEntity;
import com.artsykov.fapi.entity.LogInInfEntity;
import com.artsykov.fapi.entity.RoleEnum;
import com.artsykov.fapi.models.CompanyModel;

public class CompanyConverter {
    public static CompanyEntity convertFromFrontToBack(CompanyModel companyModel) {
        if (companyModel != null) {
            CompanyEntity companyEntity = new CompanyEntity();
            if (companyModel.getId() != 0) {
                companyEntity.setIdCompany(companyModel.getId());
            }
            companyEntity.setCompanyName(companyModel.getName());
            companyEntity.setWalletByIdWallet(companyModel.getWallet());
            LogInInfEntity logInInfEntity = new LogInInfEntity();
            if (companyModel.getIdLogInInf() != 0) {
                logInInfEntity.setIdLogInInf(companyModel.getIdLogInInf());
            }
            logInInfEntity.setEmail(companyModel.getEmail());
            logInInfEntity.setPassword(companyModel.getPassword());
            logInInfEntity.setRole(RoleEnum.COMPANY);
            companyEntity.setLogInInfByLogInInf(logInInfEntity);
            companyEntity.setIsActive((byte)companyModel.getIsActive());
            return companyEntity;
        } else {
            return null;
        }
    }

    public static CompanyModel convertFromBackToFront(CompanyEntity companyEntity) {
        if (companyEntity != null) {
            CompanyModel companyModel = new CompanyModel();
            companyModel.setId(companyEntity.getIdCompany());
            companyModel.setEmail(companyEntity.getLogInInfByLogInInf().getEmail());
            companyModel.setName(companyEntity.getCompanyName());
            companyModel.setPassword(null);
            companyModel.setWallet(companyEntity.getWalletByIdWallet());
            companyModel.setIdLogInInf(companyEntity.getLogInInfByLogInInf().getIdLogInInf());
            companyModel.setIsActive(companyEntity.getIsActive());
            return companyModel;
        } else {
            return null;
        }
    }
}

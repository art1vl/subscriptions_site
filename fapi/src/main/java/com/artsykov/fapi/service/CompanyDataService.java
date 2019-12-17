package com.artsykov.fapi.service;

import com.artsykov.fapi.models.CompanyModel;
import com.artsykov.fapi.models.CompanyOrErrorsModel;
import com.artsykov.fapi.models.WalletModel;

public interface CompanyDataService {
    CompanyOrErrorsModel saveCompany (CompanyModel company);

    CompanyModel saveWallet(CompanyModel companyModel);

    CompanyModel getCompanyByLogInInfId(int logInInfId);

    CompanyModel findCompanyById(int id);

    WalletModel findWalletByCompanyId(int companyId);
}

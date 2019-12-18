package com.artsykov.fapi.service;

import com.artsykov.fapi.models.CompanyModel;
import com.artsykov.fapi.models.CompanyOrErrorsModel;
import com.artsykov.fapi.models.CompanyPageModel;
import com.artsykov.fapi.models.WalletModel;

public interface CompanyDataService {
    CompanyOrErrorsModel saveCompany (CompanyModel company);

    CompanyModel saveWallet(CompanyModel companyModel);

    CompanyModel getCompanyByLogInInfId(int logInInfId);

    CompanyModel findCompanyById(int id);

    CompanyPageModel findAllByPage(int pageNumber, int amount);

    WalletModel findWalletByCompanyId(int companyId);

    void changeStatus(CompanyModel companyModel);
}

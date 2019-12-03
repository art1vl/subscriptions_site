package com.artsykov.fapi.service;

import com.artsykov.fapi.models.CompanyModel;
import com.artsykov.fapi.models.CompanyOrErrorsModel;

public interface CompanyDataService {
    CompanyModel saveCompany (CompanyModel company);

    CompanyModel saveWallet(CompanyModel companyModel);
}

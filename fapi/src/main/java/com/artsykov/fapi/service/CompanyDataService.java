package com.artsykov.fapi.service;

import com.artsykov.fapi.models.CompanyModel;
import com.artsykov.fapi.models.CompanyOrErrorsModel;

public interface CompanyDataService {
    CompanyOrErrorsModel saveCompany (CompanyModel company);
}

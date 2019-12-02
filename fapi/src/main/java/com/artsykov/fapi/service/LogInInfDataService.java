package com.artsykov.fapi.service;

import com.artsykov.fapi.models.CustomerOrCompanyOrAdminOrErrorsModel;

public interface LogInInfDataService {
    CustomerOrCompanyOrAdminOrErrorsModel findUserByEmail(String email, String password);
}

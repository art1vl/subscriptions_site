package com.artsykov.fapi.service;

import com.artsykov.fapi.entity.LogInInfEntity;
import com.artsykov.fapi.models.CustomerOrCompanyOrAdminOrErrorsModel;
import org.springframework.security.core.userdetails.UserDetails;

public interface LogInInfDataService {
    LogInInfEntity findUserByEmail(String email);

    public UserDetails loadUserByUsername(String email);
}

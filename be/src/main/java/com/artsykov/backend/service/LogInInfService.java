package com.artsykov.backend.service;

import com.artsykov.backend.entity.LogInInfEntity;

public interface LogInInfService {
    LogInInfEntity findByEmail (String email);
}

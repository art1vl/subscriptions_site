package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.LogInInfEntity;
import com.artsykov.backend.repository.LogInInfRepository;
import com.artsykov.backend.service.LogInInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInInfServiceImpl implements LogInInfService {
    private LogInInfRepository logInInfRepository;

    @Autowired
    public LogInInfServiceImpl(LogInInfRepository logInInfRepository) {
        this.logInInfRepository = logInInfRepository;
    }

    @Override
    public LogInInfEntity findByEmail(String email) {
        return logInInfRepository.findByEmail(email);
    }
}

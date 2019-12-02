package com.artsykov.backend.controller;

import com.artsykov.backend.entity.LogInInfEntity;
import com.artsykov.backend.service.LogInInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log_in_inf")
public class LogInInfController {
    private LogInInfService logInInfService;

    @Autowired
    public LogInInfController (LogInInfService logInInfService) {
        this.logInInfService = logInInfService;
    }

    @RequestMapping(value = "/{email}")
    public ResponseEntity<LogInInfEntity> findByEmail(@PathVariable("email") String email) throws InterruptedException {
        return ResponseEntity.ok(logInInfService.findByEmail(email));
    }
}

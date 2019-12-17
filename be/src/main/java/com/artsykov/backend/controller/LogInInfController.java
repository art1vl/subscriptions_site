package com.artsykov.backend.controller;

import com.artsykov.backend.entity.LogInInfEntity;
import com.artsykov.backend.service.LogInInfService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log/in/inf")
public class LogInInfController {
    @Autowired
    private LogInInfService logInInfService;

//    @Autowired
//    public LogInInfController (LogInInfService logInInfService) {
//        this.logInInfService = logInInfService;
//    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<LogInInfEntity> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(logInInfService.findByEmail(email));
    }

    @GetMapping(value = "/exist/{email}")
    public ResponseEntity<Boolean> isExistByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(logInInfService.isEmailExist(email));
    }
}

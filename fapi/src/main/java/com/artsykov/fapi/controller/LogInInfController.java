package com.artsykov.fapi.controller;

import com.artsykov.fapi.controller.handler.HandlerService;
import com.artsykov.fapi.entity.LogInInfEntity;
import com.artsykov.fapi.models.CustomerOrCompanyOrAdminOrErrorsModel;
import com.artsykov.fapi.models.LogInParam;
import com.artsykov.fapi.security.JwtTokenProvider;
import com.artsykov.fapi.service.LogInInfDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/logInInf")
public class LogInInfController {
    private LogInInfDataService logInInfDataService;
    private HandlerService handlerService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LogInInfController(LogInInfDataService logInInfDataService,
                              HandlerService handlerService,
                              AuthenticationManager authenticationManager,
                              JwtTokenProvider jwtTokenProvider,
                              BCryptPasswordEncoder passwordEncoder) {
        this.logInInfDataService = logInInfDataService;
        this.handlerService = handlerService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Secured({"ROLE_COMPANY", "ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping(value = "/{token}")
    public ResponseEntity<CustomerOrCompanyOrAdminOrErrorsModel> getUserByToken(@PathVariable("token") String token) {
            return ResponseEntity.ok(logInInfDataService.getUserByToken(token));
    }

    @PostMapping(value = "/sign/in")
    public ResponseEntity<?> getUserByEmail(@RequestBody @Valid LogInParam logInParam) throws InterruptedException {
        Map<Object, Object> response = new HashMap<>();
        try {
            String username = logInParam.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, logInParam.getPassword()));
            LogInInfEntity logInInfEntity = logInInfDataService.findUserByEmail(username);

            if (logInInfEntity == null) {
                response.put("error", "Incorrect email or password");
            }
            String token = jwtTokenProvider.createToken(username, logInInfEntity.getRole().toString());

            logInInfEntity.setPassword(null);
            response.put("user", logInInfEntity);
            response.put("token", token);
            response.put("error", null);
        } catch (AuthenticationException e) {
            response.put("error", "Incorrect email or password");
        }
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomerOrCompanyOrAdminOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(new CustomerOrCompanyOrAdminOrErrorsModel(handlerService.handleMethodArgumentNotValid(ex)));
    }
}

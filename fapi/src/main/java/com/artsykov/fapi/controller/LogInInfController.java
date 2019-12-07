package com.artsykov.fapi.controller;

import com.artsykov.fapi.controller.handler.HandlerService;
import com.artsykov.fapi.entity.LogInInfEntity;
import com.artsykov.fapi.models.CustomerOrCompanyOrAdminOrErrorsModel;
import com.artsykov.fapi.security.JwtTokenProvider;
import com.artsykov.fapi.service.LogInInfDataService;
import com.artsykov.fapi.models.LogInParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/logInInf")
public class LogInInfController {

    @Autowired
    private LogInInfDataService logInInfDataService;

    @Autowired
    private HandlerService handlerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/sign/in")
    public ResponseEntity<?> getUserByEmail(@RequestBody @Valid LogInParam logInParam) throws InterruptedException {
        try {
            String username = logInParam.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, logInParam.getPassword()));
            LogInInfEntity logInInfEntity = logInInfDataService.findUserByEmail(username);

            if (logInInfEntity == null) {
                throw new UsernameNotFoundException("User with email: " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username, logInInfEntity.getRole().toString());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("user", logInInfEntity);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomerOrCompanyOrAdminOrErrorsModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(new CustomerOrCompanyOrAdminOrErrorsModel(handlerService.handleMethodArgumentNotValid(ex)));
    }
}

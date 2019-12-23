package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.entity.LogInInfEntity;
import com.artsykov.fapi.models.AdminModel;
import com.artsykov.fapi.models.CustomerOrCompanyOrAdminOrErrorsModel;
import com.artsykov.fapi.service.CompanyDataService;
import com.artsykov.fapi.service.CustomerDataService;
import com.artsykov.fapi.service.LogInInfDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service("logInIndService")
public class LogInInfDataServiceImpl implements LogInInfDataService, UserDetailsService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    private CustomerDataService customerDataService;
    private CompanyDataService companyDataService;

    @Autowired
    public LogInInfDataServiceImpl(CustomerDataService customerDataService,
                                   CompanyDataService companyDataService) {
        this.customerDataService = customerDataService;
        this.companyDataService = companyDataService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LogInInfEntity logInInfEntity = findUserByEmail(email);
        if (logInInfEntity == null) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }
        return new org.springframework.security.core.userdetails.User(logInInfEntity.getEmail(), logInInfEntity.getPassword(), getAuthority(logInInfEntity));
    }

    private Set<SimpleGrantedAuthority> getAuthority(LogInInfEntity logInInfEntity) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + logInInfEntity.getRole()));
        return authorities;
    }

    @Override
    public LogInInfEntity findUserByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/log/in/inf/" + email, LogInInfEntity.class);
    }

    @Override
    public CustomerOrCompanyOrAdminOrErrorsModel getUserByToken(String token) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) usernamePasswordAuthenticationToken.getPrincipal();
        String email = userDetails.getUsername();
        LogInInfEntity logInInfEntity = findUserByEmail(email);
        CustomerOrCompanyOrAdminOrErrorsModel model = new CustomerOrCompanyOrAdminOrErrorsModel();
        model.setCustomerModel(customerDataService.findCustomerByLogInInfId(logInInfEntity.getIdLogInInf()));
        model.setCompanyModel(companyDataService.getCompanyByLogInInfId(logInInfEntity.getIdLogInInf()));
        if (model.getCompanyModel() == null && model.getCustomerModel() == null) {
            model.setAdminModel(new AdminModel(logInInfEntity.getIdLogInInf()));
        }
        return model;
    }
}

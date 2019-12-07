package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.CustomerConverter;
import com.artsykov.fapi.entity.CompanyEntity;
import com.artsykov.fapi.entity.CustomerEntity;
import com.artsykov.fapi.entity.LogInInfEntity;
import com.artsykov.fapi.converter.CompanyConverter;
import com.artsykov.fapi.models.AdminModel;
import com.artsykov.fapi.models.CustomerOrCompanyOrAdminOrErrorsModel;
import com.artsykov.fapi.security.JwtTokenProvider;
import com.artsykov.fapi.service.LogInInfDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service("logInIndService")
public class LogInInfDataServiceImpl implements LogInInfDataService, UserDetailsService {
    private CustomerConverter customerConverter;
    private CompanyConverter companyConverter;
   // private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    public LogInInfDataServiceImpl (CustomerConverter customerConverter, CompanyConverter companyConverter) {
        this.customerConverter = customerConverter;
        this.companyConverter = companyConverter;
      //  this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LogInInfEntity logInInfEntity = findUserByEmail(email);
        if(logInInfEntity == null){
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
//        CustomerOrCompanyOrAdminOrErrorsModel customerOrCompanyOrAdminOrErrorsModel = new CustomerOrCompanyOrAdminOrErrorsModel();
//        AdminModel adminModel = new AdminModel();
//        Map<String, String> errorsMap = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
       // password = bCryptPasswordEncoder.encode(password);
        LogInInfEntity logInInfEntity = restTemplate.getForObject(backendServerUrl + "/api/log_in_inf/" + email, LogInInfEntity.class);
//        if (logInInfEntity != null) {
//            if (logInInfEntity.getPassword().equals(password)) {
//                switch (logInInfEntity.getRole()) {
//                    case ADMIN:
//                        adminModel.setIdLogInInf(logInInfEntity.getIdLogInInf());
//                        customerOrCompanyOrAdminOrErrorsModel.setAdminModel(adminModel);
//                        break;
//                    case CUSTOMER:
//                        customerOrCompanyOrAdminOrErrorsModel.setCustomerModel(customerConverter.convertFromBackToFront(restTemplate.getForObject(
//                                backendServerUrl + "/api/customer/log/in/inf/" + logInInfEntity.getIdLogInInf(), CustomerEntity.class)));
//                        break;
//                    case COMPANY:
//                        customerOrCompanyOrAdminOrErrorsModel.setCompanyModel(companyConverter.convertFromBackToFront(restTemplate.getForObject(
//                                backendServerUrl + "/api/company/log/in/inf/" + logInInfEntity.getIdLogInInf(), CompanyEntity.class)));
//                        break;
//
//                }
//            } else {
//                errorsMap.put("password", "Email or password is incorrect");
//            }
//        } else {
//            errorsMap.put("email", "Email or password is incorrect");
//        }
//        if (!errorsMap.isEmpty()) {
//            customerOrCompanyOrAdminOrErrorsModel.setErrors(errorsMap);
//        }
        return logInInfEntity;
    }
}

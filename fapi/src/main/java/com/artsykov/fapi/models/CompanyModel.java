package com.artsykov.fapi.models;

import com.artsykov.fapi.entity.WalletEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class CompanyModel {
    private int id;

    @NotEmpty(message = "Company name is required")
    @Pattern(regexp = "^[A-Z a-z0-9]+$",
            message = "Company name is invalid")
    private String name;
    private int isActive;
    private int idLogInInf;
    private WalletEntity wallet;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is incorrect")
    private String email;

    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^[A-Za-z0-9]{8,}$",
            message = "Password is invalid")
    private String password;
}

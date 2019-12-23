package com.artsykov.fapi.models;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CompanyModel {
    @Min(value=0, message="Incorrect company id")
    private int id;

    @NotEmpty(message = "Company name is required")
    @Pattern(regexp = "^[A-Z a-z0-9]+$",
            message = "Company name is invalid")
    private String name;

    @Min(value=0, message="Incorrect active status")
    @Max(value=1, message="Incorrect active status")
    private int isActive;

    @Min(value=0, message="Incorrect log in information id")
    private int idLogInInf;
    private WalletModel wallet;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is incorrect")
    private String email;
    
    @Pattern(regexp = "^[A-Za-z0-9]{8,}$",
            message = "Password is invalid")
    private String password;
}

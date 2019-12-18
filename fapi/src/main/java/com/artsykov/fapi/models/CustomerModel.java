package com.artsykov.fapi.models;

import com.artsykov.fapi.entity.WalletEntity;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CustomerModel {
    @Min(value=0, message="Incorrect customer id")
    private int id;

    @NotEmpty(message = "Name is required")
    @Pattern(regexp = "^[A-Z]{1}[a-z]+$",
            message = "Name is invalid")
    private String name;

    @NotEmpty(message = "Surname is required")
    @Pattern(regexp = "^[A-Z]{1}[a-z]+$",
            message = "Surname is invalid")
    private String surname;

    @Positive(message = "Age is invalid")
    private int age;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is incorrect")
    private String email;

    @Pattern(regexp="^[A-Za-z0-9]{8,}$",
            message="Password is invalid")
    private String password;
    private WalletModel wallet;

    @Min(value=0, message="Incorrect active status")
    @Max(value=1, message="Incorrect active status")
    private int isActive;

    @Min(value=0, message="Incorrect log in information id")
    private int idLogInInf;

    public CustomerModel() {}
}

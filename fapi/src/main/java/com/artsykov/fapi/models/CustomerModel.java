package com.artsykov.fapi.models;

import com.artsykov.fapi.entity.WalletEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
public class CustomerModel {
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

    @NotEmpty(message = "Password is required")
    @Pattern(regexp="^[A-Za-z0-9]{8,}$",
            message="Password is invalid")
    private String password;
    private WalletEntity wallet;
    private int isActive;
    private int idLogInInf;

    public CustomerModel() {

    }

    public CustomerModel(int id, String name, String surname, int age, String email, String password,
                         WalletEntity wallet, int isActive, int idLogInInf) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.wallet = wallet;
        this.isActive = isActive;
        this.idLogInInf = idLogInInf;
    }
}

package com.artsykov.fapi.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class LogInParam {

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is incorrect")
    private String email;

    @NotEmpty(message = "Password is required")
    @Pattern(regexp="^[A-Za-z0-9]{8,}$",
            message="Password is invalid")
    private String password;
}

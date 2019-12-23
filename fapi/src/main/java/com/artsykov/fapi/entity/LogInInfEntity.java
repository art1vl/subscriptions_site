package com.artsykov.fapi.entity;

import lombok.Data;

@Data
public class LogInInfEntity {
    private int idLogInInf;
    private String email;
    private String password;
    private RoleEnum role;
}

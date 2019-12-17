package com.artsykov.fapi.models;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class AdminModel {
    @Min(value=0, message="Incorrect admin id")
    private int idLogInInf;

    public AdminModel(int idLogInInf) {
        this.idLogInInf = idLogInInf;
    }
}

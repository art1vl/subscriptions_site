package com.artsykov.fapi.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;

@Data
public class AdminModel {
    @Min(value=0, message="Incorrect admin id")
    private int idLogInInf;
}

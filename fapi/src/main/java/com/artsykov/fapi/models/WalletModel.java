package com.artsykov.fapi.models;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
public class WalletModel {
    @Min(value=0, message="Incorrect wallet id")
    private int idWallet;

    @NotEmpty(message = "Balance is required")
    @Positive(message = "Balance can't be negative")
    private int balance;

    @NotEmpty(message = "Card number is required")
    @Positive(message = "Card number can't be negative")
    private long cardNumber;

    @NotEmpty(message = "Card date ir required")
    private Date cardDate;

    @NotEmpty(message = "Cvv code is required")
    @Positive(message = "Cvv code can't be negative")
    private int cardCvvCode;

    @NotEmpty(message = "Holder name is required")
    @Pattern(regexp = "^[A-Z]+\\s[A-Z]+$",
            message = "Holder name is incorrect")
    private String personName;
}

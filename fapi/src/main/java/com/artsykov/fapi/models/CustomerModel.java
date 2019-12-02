package com.artsykov.fapi.models;

import com.artsykov.fapi.entity.WalletEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CustomerModel {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
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

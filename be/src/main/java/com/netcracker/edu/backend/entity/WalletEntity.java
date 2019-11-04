package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "wallet", schema = "backend", catalog = "")
public class WalletEntity {
    private int idWallet;
    private int balance;
    private int cartNumber;
    private Timestamp cartDate;
    private int cartCvCode;
//    private Collection<CompanyEntity> companiesByIdWallet;
//    private Collection<CustomerEntity> costumersByIdWallet;

    @Id
    @Column(name = "id_wallet")
    public int getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(int idWallet) {
        this.idWallet = idWallet;
    }

    @Basic
    @Column(name = "balance")
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "cart_number")
    public int getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(int cartNumber) {
        this.cartNumber = cartNumber;
    }

    @Basic
    @Column(name = "cart_date")
    public Timestamp getCartDate() {
        return cartDate;
    }

    public void setCartDate(Timestamp cartDate) {
        this.cartDate = cartDate;
    }

    @Basic
    @Column(name = "cart_cv_code")
    public int getCartCvCode() {
        return cartCvCode;
    }

    public void setCartCvCode(int cartCvCode) {
        this.cartCvCode = cartCvCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletEntity that = (WalletEntity) o;
        return idWallet == that.idWallet &&
                balance == that.balance &&
                cartNumber == that.cartNumber &&
                cartCvCode == that.cartCvCode &&
                Objects.equals(cartDate, that.cartDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWallet, balance, cartNumber, cartDate, cartCvCode);
    }

//    @OneToMany(mappedBy = "walletByIdWallet")
//    public Collection<CompanyEntity> getCompaniesByIdWallet() {
//        return companiesByIdWallet;
//    }
//
//    public void setCompaniesByIdWallet(Collection<CompanyEntity> companiesByIdWallet) {
//        this.companiesByIdWallet = companiesByIdWallet;
//    }
//
//    @OneToMany(mappedBy = "walletByIdWallet")
//    public Collection<CustomerEntity> getCostumersByIdWallet() {
//        return costumersByIdWallet;
//    }
//
//    public void setCostumersByIdWallet(Collection<CustomerEntity> costumersByIdWallet) {
//        this.costumersByIdWallet = costumersByIdWallet;
//    }
}

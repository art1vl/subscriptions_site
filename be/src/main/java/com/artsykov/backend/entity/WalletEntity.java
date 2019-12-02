package com.artsykov.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "wallet", schema = "backend", catalog = "")
public class WalletEntity {
    private int idWallet;
    private int balance;
    private long cardNumber;
    private LocalDate cardDate;
    private int cardCvvCode;
    private String personName;
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
    @Column(name = "card_number")
    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cartNumber) {
        this.cardNumber = cartNumber;
    }

    @Basic
    @Column(name = "card_date", columnDefinition = "TIMESTAMP")
    public LocalDate getCardDate() {
        return cardDate;
    }

    public void setCardDate(LocalDate cartDate) {
        this.cardDate = cartDate;
    }

    @Basic
    @Column(name = "card_cvv_code")
    public int getCardCvvCode() {
        return cardCvvCode;
    }

    public void setCardCvvCode(int cartCvCode) {
        this.cardCvvCode = cartCvCode;
    }

    @Basic
    @Column(name = "person_name")
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletEntity that = (WalletEntity) o;
        return idWallet == that.idWallet &&
                balance == that.balance &&
                cardNumber == that.cardNumber &&
                cardCvvCode == that.cardCvvCode &&
                Objects.equals(cardDate, that.cardDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWallet, balance, cardNumber, cardDate, cardCvvCode);
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

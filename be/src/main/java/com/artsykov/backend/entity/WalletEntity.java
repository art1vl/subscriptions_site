package com.artsykov.backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "wallet", schema = "backend", catalog = "")
public class WalletEntity {
    private int idWallet;
    private int balance;
    private long cardNumber;
    private Date cardDate;
    private int cardCvvCode;
    private String personName;
    private int debt;
//    private Collection<CompanyEntity> companiesByIdWallet;
//    private Collection<CustomerEntity> costumersByIdWallet;

    @Id
    @Column(name = "id_wallet")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "debt")
    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
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
    public Date getCardDate() {
        return cardDate;
    }

    public void setCardDate(Date cartDate) {
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

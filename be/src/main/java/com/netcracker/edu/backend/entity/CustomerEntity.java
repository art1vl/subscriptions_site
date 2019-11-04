package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "backend", catalog = "")
public class CustomerEntity {
    private int idCustomer;
    private String name;
    private String surname;
    private int age;
    private byte isActive;
//    private int logInInf;
//    private int idWallet;
    private LogInInfEntity logInInfByLogInInf;
    private WalletEntity walletByIdWallet;
//    private Collection<SubscriptionEntity> subscriptionsByIdCostumer;

    @Id
    @Column(name = "id_customer")
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCostumer) {
        this.idCustomer = idCostumer;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

//    @Basic
//    @Column(name = "log_in_inf")
//    public int getLogInInf() {
//        return logInInf;
//    }
//
//    public void setLogInInf(int logInInf) {
//        this.logInInf = logInInf;
//    }

//   @Basic
//    @Column(name = "id_wallet")
//    public int getIdWallet() {
//        return idWallet;
//    }
//
//    public void setIdWallet(int idWallet) {
//        this.idWallet = idWallet;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return idCustomer == that.idCustomer &&
                age == that.age &&
                isActive == that.isActive &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, name, surname, age, isActive);
    }

    @ManyToOne
    @JoinColumn(name = "log_in_inf", referencedColumnName = "id_log_in_inf", nullable = false)
    public LogInInfEntity getLogInInfByLogInInf() {
        return logInInfByLogInInf;
    }

    public void setLogInInfByLogInInf(LogInInfEntity logInInfByLogInInf) {
        this.logInInfByLogInInf = logInInfByLogInInf;
    }

    @ManyToOne
    @JoinColumn(name = "id_wallet", referencedColumnName = "id_wallet")
    public WalletEntity getWalletByIdWallet() {
        return walletByIdWallet;
    }

    public void setWalletByIdWallet(WalletEntity walletByIdWallet) {
        this.walletByIdWallet = walletByIdWallet;
    }

//    @OneToMany(mappedBy = "costumerByIdCostumer")
//    public Collection<SubscriptionEntity> getSubscriptionsByIdCostumer() {
//        return subscriptionsByIdCostumer;
//    }
//
//    public void setSubscriptionsByIdCostumer(Collection<SubscriptionEntity> subscriptionsByIdCostumer) {
//        this.subscriptionsByIdCostumer = subscriptionsByIdCostumer;
//    }
}

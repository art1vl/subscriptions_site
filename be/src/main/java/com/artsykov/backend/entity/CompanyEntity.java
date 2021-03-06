package com.artsykov.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "company", schema = "backend", catalog = "")
public class CompanyEntity {
    private int idCompany;
    private String companyName;
    private byte isActive;
//    private int logInInf;
//    private int idWallet;
    private LogInInfEntity logInInf;
    private WalletEntity walletByIdWallet;
//    private Collection<ProductEntity> productsByIdCompany;


    @Id
    @Column(name = "id_company")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

//    @Basic
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
        CompanyEntity that = (CompanyEntity) o;
        return idCompany == that.idCompany &&
                Objects.equals(companyName, that.companyName);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(idCompany, companyName, logInInf, idWallet);
//    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "log_in_inf", referencedColumnName = "id_log_in_inf", nullable = false)
    public LogInInfEntity getLogInInf() {
        return logInInf;
    }

    public void setLogInInf(LogInInfEntity logInInfByLogInInf) {
        this.logInInf = logInInfByLogInInf;
    }

    @ManyToOne
    @JoinColumn(name = "id_wallet", referencedColumnName = "id_wallet")
    public WalletEntity getWalletByIdWallet() {
        return walletByIdWallet;
    }

    public void setWalletByIdWallet(WalletEntity walletByIdWallet) {
        this.walletByIdWallet = walletByIdWallet;
    }

//    @OneToMany(mappedBy = "companyByCompany")
//    public Collection<ProductEntity> getProductsByIdCompany() {
//        return productsByIdCompany;
//    }
//
//    public void setProductsByIdCompany(Collection<ProductEntity> productsByIdCompany) {
//        this.productsByIdCompany = productsByIdCompany;
//    }
}

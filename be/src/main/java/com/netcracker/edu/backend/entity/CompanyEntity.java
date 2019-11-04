package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "company", schema = "backend", catalog = "")
public class CompanyEntity {
    private int idCompany;
    private String companyName;
    private int logInInf;
    private int idWallet;
    private LogInInfEntity logInInfByLogInInf;
    private WalletEntity walletByIdWallet;
    private Collection<ProductEntity> productsByIdCompany;

    @Id
    @Column(name = "id_company")
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
    @Column(name = "log_in_inf")
    public int getLogInInf() {
        return logInInf;
    }

    public void setLogInInf(int logInInf) {
        this.logInInf = logInInf;
    }

    @Basic
    @Column(name = "id_wallet")
    public int getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(int idWallet) {
        this.idWallet = idWallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return idCompany == that.idCompany &&
                logInInf == that.logInInf &&
                idWallet == that.idWallet &&
                Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompany, companyName, logInInf, idWallet);
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
    @JoinColumn(name = "id_wallet", referencedColumnName = "id_wallet", nullable = false)
    public WalletEntity getWalletByIdWallet() {
        return walletByIdWallet;
    }

    public void setWalletByIdWallet(WalletEntity walletByIdWallet) {
        this.walletByIdWallet = walletByIdWallet;
    }

    @OneToMany(mappedBy = "companyByCompany")
    public Collection<ProductEntity> getProductsByIdCompany() {
        return productsByIdCompany;
    }

    public void setProductsByIdCompany(Collection<ProductEntity> productsByIdCompany) {
        this.productsByIdCompany = productsByIdCompany;
    }
}

package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "log_in_inf", schema = "backend", catalog = "")
public class LogInInfEntity {
    private int idLogInInf;
    private int email;
    private String password;
    private int role;

    @Id
    @Column(name = "id_log_in_inf")
    public int getIdLogInInf() {
        return idLogInInf;
    }

    public void setIdLogInInf(int idLogInInf) {
        this.idLogInInf = idLogInInf;
    }

    @Basic
    @Column(name = "e_mail")
    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogInInfEntity that = (LogInInfEntity) o;
        return idLogInInf == that.idLogInInf &&
                email == that.email &&
                role == that.role &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLogInInf, email, password, role);
    }

//    @OneToMany(mappedBy = "logInInfByLogInInf")
//    public Collection<CompanyEntity> getCompaniesByIdLogInInf() {
//        return companiesByIdLogInInf;
//    }
//
//    public void setCompaniesByIdLogInInf(Collection<CompanyEntity> companiesByIdLogInInf) {
//        this.companiesByIdLogInInf = companiesByIdLogInInf;
//    }
//
//    @OneToMany(mappedBy = "logInInfByLogInInf")
//    public Collection<CustomerEntity> getCostumersByIdLogInInf() {
//        return costumersByIdLogInInf;
//    }
//
//    public void setCostumersByIdLogInInf(Collection<CustomerEntity> costumersByIdLogInInf) {
//        this.costumersByIdLogInInf = costumersByIdLogInInf;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "role", referencedColumnName = "id_role", nullable = false)
//    public RolesEntity getRolesByRole() {
//        return rolesByRole;
//    }

}

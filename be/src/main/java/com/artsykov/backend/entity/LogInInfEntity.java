package com.artsykov.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "log_in_inf", schema = "backend", catalog = "")
public class LogInInfEntity {
    private int idLogInInf;
    private String email;
    private String password;

    private RoleEnum role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log_in_inf")
    public int getIdLogInInf() {
        return idLogInInf;
    }

    public void setIdLogInInf(int idLogInInf) {
        this.idLogInInf = idLogInInf;
    }

    @Basic
    @Column(name = "e_mail")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    @Column(name="role", nullable=false)
    @Enumerated(EnumType.STRING)
    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    /*@ManyToOne
        @Column(name = "role")
        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }
    */
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

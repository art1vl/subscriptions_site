package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roles", schema = "backend")
public class RolesEntity {
    private int idRole;
    private String nameOfRole;
//    private Collection<LogInInfEntity> logInInfsByIdRole;

    @Id
    @Column(name = "id_role")
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Basic
    @Column(name = "name_of_role")
    public String getNameOfRole() {
        return nameOfRole;
    }

    public void setNameOfRole(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesEntity that = (RolesEntity) o;
        return idRole == that.idRole &&
                Objects.equals(nameOfRole, that.nameOfRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, nameOfRole);
    }

//    @OneToMany(mappedBy = "rolesByRole")
//    public Collection<LogInInfEntity> getLogInInfsByIdRole() {
//        return logInInfsByIdRole;
//    }
//
//    public void setLogInInfsByIdRole(Collection<LogInInfEntity> logInInfsByIdRole) {
//        this.logInInfsByIdRole = logInInfsByIdRole;
//    }
}

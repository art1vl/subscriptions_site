package com.artsykov.backend.repository;

import com.artsykov.backend.entity.CompanyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<CompanyEntity, Integer> {
    CompanyEntity findByLogInInfIdLogInInf(@Param("log_in_inf") int logInInfId);

    CompanyEntity findByIdCompany(@Param("id_company") int companyId);
}

package com.artsykov.backend.repository;

import com.artsykov.backend.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
    CompanyEntity findByLogInInfIdLogInInf(@Param("log_in_inf") int logInInfId);

    CompanyEntity findByIdCompany(@Param("id_company") int companyId);

    Page<CompanyEntity> findAll(Pageable pageable);

    CompanyEntity findByCompanyName(@Param("company_name") String companyName);
}

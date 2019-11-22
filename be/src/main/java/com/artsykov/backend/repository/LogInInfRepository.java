package com.artsykov.backend.repository;

import com.artsykov.backend.entity.LogInInfEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogInInfRepository extends CrudRepository<LogInInfEntity, Integer> {
//    @Query(value = "SELECT *  FROM backend.log_in_inf WHERE e_mail = ?1",
//            nativeQuery=true)
    LogInInfEntity findByEmail(String email);
}

package com.artsykov.backend.repository;

import com.artsykov.backend.entity.LogInInfEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogInInfRepository extends CrudRepository<LogInInfEntity, Integer> {
    LogInInfEntity findByEmail(String email);

    boolean existsByEmail(String email);
}

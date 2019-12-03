package com.artsykov.backend.repository;

import com.artsykov.backend.entity.ProductTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends CrudRepository<ProductTypeEntity, Integer> {
}

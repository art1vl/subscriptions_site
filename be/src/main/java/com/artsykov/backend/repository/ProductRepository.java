package com.artsykov.backend.repository;

import com.artsykov.backend.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    ProductEntity findByIdProduct(@Param("id_product") int id);
}

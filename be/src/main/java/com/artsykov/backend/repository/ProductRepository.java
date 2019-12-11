package com.artsykov.backend.repository;

import com.artsykov.backend.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    ProductEntity findByIdProduct(@Param("id_product") int id);

    Page<ProductEntity> findAll(Pageable pageable);
}

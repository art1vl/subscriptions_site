package com.artsykov.backend.repository;

import com.artsykov.backend.entity.CompanyEntity;
import com.artsykov.backend.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    ProductEntity findByIdProduct(@Param("id_product") int id);

    Page<ProductEntity> findAllByIsActive(byte status, Pageable pageable);

    Page<ProductEntity> findAllByCompany(CompanyEntity companyEntity, Pageable pageable);

    Page<ProductEntity> findAll(Pageable pageable);

    void deleteByIdProduct(@Param("id_product") int id);

    List<ProductEntity> findAllByCompany(CompanyEntity companyEntity);

    @Query(value = "SELECT p FROM ProductEntity p INNER JOIN CompanyEntity c on p.company.idCompany = c.idCompany WHERE " +
            "p.isActive = 1 and (:productName is null or p.productName = :productName) " +
            "and (:companyName is null or c.companyName = :companyName) and (:minValue is null or p.cost >= :minValue) and" +
            " (:maxValue is null or p.cost <= :maxValue) and " +
            "(:productType is null or p.type.typeName = :productType)")
    Page<ProductEntity> findAllBySearchByPage(@Param("productName") String productName,
                                              @Param("companyName") String companyName,
                                              @Param("minValue") Integer minValue,
                                              @Param("maxValue") Integer maxValue,
                                              @Param("productType") String productTypeEntity,
                                              Pageable pageable);
}

//+
//        " and (:companyName is null or company.company_name = :companyName) and product.cost BETWEEN" +
//        "(:minValue is null or :minValue) and (:maxValue is null or :maxValue) and " +
//        "(:productType is null or product.type = :productType)"
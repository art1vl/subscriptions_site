package com.artsykov.backend.repository;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.entity.SubscriptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Integer> {
    SubscriptionEntity findByProductByIdProductAndCustomerByIdCustomer(ProductEntity product, CustomerEntity customer);

    Page<SubscriptionEntity> findAllByCustomerByIdCustomer(CustomerEntity customerEntity, Pageable pageable);

    void deleteAllByProductByIdProduct(ProductEntity productEntity);

    List<SubscriptionEntity> findAllByProductByIdProduct(ProductEntity productEntity);

    List<SubscriptionEntity> findAllByCustomerByIdCustomer(CustomerEntity customerEntity);
}

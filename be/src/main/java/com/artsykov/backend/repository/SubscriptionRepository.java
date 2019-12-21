package com.artsykov.backend.repository;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.entity.SubscriptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Integer> {
    SubscriptionEntity findByIdSubscription(int id);

    SubscriptionEntity findByProductByIdProductAndCustomerByIdCustomer(ProductEntity product, CustomerEntity customer);

    Page<SubscriptionEntity> findAllByCustomerByIdCustomer(CustomerEntity customerEntity, Pageable pageable);

    void deleteAllByProductByIdProduct(ProductEntity productEntity);

    List<SubscriptionEntity> findAllByProductByIdProduct(ProductEntity productEntity);

    List<SubscriptionEntity> findAllByCustomerByIdCustomer(CustomerEntity customerEntity);

    List<SubscriptionEntity> findAllByCustomerByIdCustomerAndNextPayDateAndIsActive(CustomerEntity customerEntity,
                                                                                    Date nextPayDate,
                                                                                    byte status);

    @Query(value = "SELECT id_subscription, subscription.id_product, id_customer, start_subscript_date," +
            " subscription.is_active, next_pay_date FROM subscription JOIN product on" +
            " subscription.id_product = product.id_product WHERE subscription.is_active = ?1 and" +
            " product.is_active = 1 and subscription.id_customer = ?2", nativeQuery=true)
    List<SubscriptionEntity> findAllByCustomerByIdCustomerAndIsActive(@Param("subscription.is_active") byte status,
                                                                      @Param("subscription.id_customer") CustomerEntity customerEntity);
}

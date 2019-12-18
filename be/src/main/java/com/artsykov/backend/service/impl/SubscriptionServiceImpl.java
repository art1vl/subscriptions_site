package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.entity.SubscriptionEntity;
import com.artsykov.backend.model.CustomerSubscriptionPageModel;
import com.artsykov.backend.repository.CustomerRepository;
import com.artsykov.backend.repository.ProductRepository;
import com.artsykov.backend.repository.SubscriptionRepository;
import com.artsykov.backend.service.CustomerService;
import com.artsykov.backend.service.ProductService;
import com.artsykov.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionRepository subscriptionRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   ProductRepository productRepository,
                                   CustomerRepository customerRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public SubscriptionEntity save(SubscriptionEntity subscriptionEntity) {
        LocalDate nextPayDate = LocalDate.now().plusMonths(1);
        subscriptionEntity.setNextPayDate(Date.valueOf(nextPayDate));
        return subscriptionRepository.save(subscriptionEntity);
    }

    @Override
    public void deleteById(int id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public SubscriptionEntity findSubscription(int productId, int customerId) {
        ProductEntity productEntity = productRepository.findByIdProduct(productId);
        CustomerEntity customerEntity = customerRepository.findByIdCustomer(customerId);
        return subscriptionRepository.findByProductByIdProductAndCustomerByIdCustomer(productEntity, customerEntity);
    }

    @Override
    public CustomerSubscriptionPageModel findAllByCustomerId(int customerId, int page, int amount) {
        CustomerEntity customerEntity = customerRepository.findByIdCustomer(customerId);
        Pageable pageable = PageRequest.of(page, amount);
        Page<SubscriptionEntity> entityPage = subscriptionRepository.findAllByCustomerByIdCustomer(customerEntity, pageable);
        CustomerSubscriptionPageModel customerSubscriptionPageModel = new CustomerSubscriptionPageModel();
        customerSubscriptionPageModel.setSubscriptionEntities(entityPage.getContent());
        customerSubscriptionPageModel.setTotalElements(entityPage.getTotalElements());
        customerSubscriptionPageModel.setTotalPages(entityPage.getTotalPages());
        return customerSubscriptionPageModel;
    }

    @Override
    public void deleteByProductId(ProductEntity productEntity) {
        subscriptionRepository.deleteAllByProductByIdProduct(productEntity);
    }

    @Override
    public void changeSubscriptionStatusByProduct(ProductEntity productEntity) {
        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepository.findAllByProductByIdProduct(productEntity);
        byte status = productEntity.getIsActive();
        for(SubscriptionEntity subscription: subscriptionEntityList) {
            subscription.setIsActive(status);
            subscriptionRepository.save(subscription);
        }
    }

    @Override
    public void changeSubscriptionStatusByCustomer(CustomerEntity customerEntity) {
        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepository.findAllByCustomerByIdCustomer(customerEntity);
        byte status = customerEntity.getIsActive();
        for(SubscriptionEntity subscription: subscriptionEntityList) {
            subscription.setIsActive(status);
            subscriptionRepository.save(subscription);
        }
    }
}

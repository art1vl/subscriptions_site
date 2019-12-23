package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.entity.ProductEntity;
import com.artsykov.backend.entity.SubscriptionEntity;
import com.artsykov.backend.entity.WalletEntity;
import com.artsykov.backend.model.CustomerSubscriptionPageModel;
import com.artsykov.backend.repository.CustomerRepository;
import com.artsykov.backend.repository.ProductRepository;
import com.artsykov.backend.repository.SubscriptionRepository;
import com.artsykov.backend.repository.WalletRepository;
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
    private WalletRepository walletRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   ProductRepository productRepository,
                                   CustomerRepository customerRepository,
                                   WalletRepository walletRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public SubscriptionEntity save(SubscriptionEntity subscriptionEntity) {
        LocalDate nextPayDate = LocalDate.now().plusMonths(1);
        subscriptionEntity.setNextPayDate(Date.valueOf(nextPayDate));
        return subscriptionRepository.save(subscriptionEntity);
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
        for (SubscriptionEntity subscription : subscriptionEntityList) {
            subscription.setIsActive(status);
            subscriptionRepository.save(subscription);
        }
    }

    @Override
    public void changeSubscriptionStatusByCustomer(CustomerEntity customerEntity) {
        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepository.findAllByCustomerByIdCustomer(customerEntity);
        byte status = customerEntity.getIsActive();
        for (SubscriptionEntity subscription : subscriptionEntityList) {
            subscription.setIsActive(status);
            subscriptionRepository.save(subscription);
        }
    }

    @Override
    public void deleteById(int id) {
        SubscriptionEntity subscriptionEntity = subscriptionRepository.findByIdSubscription(id);
        byte subscriptionStatus = subscriptionEntity.getIsActive();
        subscriptionRepository.deleteById(id);
        if (subscriptionEntity.getCustomerByIdCustomer().getWalletByIdWallet().getDebt() != 0 &&
                subscriptionStatus == 0) {
            WalletEntity customerWallet = subscriptionEntity.getCustomerByIdCustomer().getWalletByIdWallet();
            customerWallet.setDebt(customerWallet.getDebt() - subscriptionEntity.getProductByIdProduct().getCost());
            if (customerWallet.getDebt() == 0) {
                CustomerEntity customerEntity = subscriptionEntity.getCustomerByIdCustomer();
                customerEntity.setIsActive((byte) 1);
                customerEntity.setWalletByIdWallet(customerWallet);
                unblockSubscriptionsAfterCustomersUnblock(customerEntity);
                customerRepository.save(customerEntity);
            } else {
                walletRepository.save(customerWallet);
            }
        }
    }

    @Override
    public void unblockSubscriptionsAfterCustomersUnblock(CustomerEntity customerEntity) {
        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepository
                .findAllByCustomerByIdCustomerAndIsActive((byte) 0, customerEntity);
        WalletEntity companyWallet;
        WalletEntity customerWallet = customerEntity.getWalletByIdWallet();
        Date nextPayDate = Date.valueOf(LocalDate.now());
        int cost;
        for (SubscriptionEntity subscription : subscriptionEntityList) {
            companyWallet = walletRepository.findWalletEntityByIdCompany(subscription.getProductByIdProduct()
                    .getCompany().getIdCompany());
            cost = subscription.getProductByIdProduct().getCost();
            companyWallet.setBalance(companyWallet.getBalance() + cost);
            subscription.setIsActive((byte) 1);
            subscription.setNextPayDate(nextPayDate);
            customerWallet.setBalance(customerWallet.getBalance() - cost);
            walletRepository.save(companyWallet);
            subscriptionRepository.save(subscription);
        }
        customerEntity.setWalletByIdWallet(customerWallet);
    }
}

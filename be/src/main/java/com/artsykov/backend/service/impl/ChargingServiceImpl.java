package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.entity.SubscriptionEntity;
import com.artsykov.backend.entity.WalletEntity;
import com.artsykov.backend.repository.CustomerRepository;
import com.artsykov.backend.repository.SubscriptionRepository;
import com.artsykov.backend.repository.WalletRepository;
import com.artsykov.backend.service.ChargingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@EnableScheduling
public class ChargingServiceImpl implements ChargingService {
    private SubscriptionRepository subscriptionRepository;
    private CustomerRepository customerRepository;
    private WalletRepository walletRepository;

    @Autowired
    public ChargingServiceImpl(SubscriptionRepository subscriptionRepository,
                               CustomerRepository customerRepository,
                               WalletRepository walletRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.customerRepository = customerRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    @Scheduled(fixedDelay = 60000, initialDelay = 60000)
    public void debit() {
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        Date now = Date.valueOf(LocalDate.now());
        Date nextPayDate = Date.valueOf(LocalDate.now());
        WalletEntity customerWallet;
        WalletEntity companyWallet;
        int cost;
        for (CustomerEntity customer : customerEntityList) {
            customerWallet = customer.getWalletByIdWallet();
            List<SubscriptionEntity> subscriptionEntityList = subscriptionRepository
                    .findAllByCustomerByIdCustomerAndNextPayDateAndIsActive(customer, now, (byte) 1);
            for (SubscriptionEntity subscription : subscriptionEntityList) {
                companyWallet = walletRepository.findWalletEntityByIdCompany(subscription.getProductByIdProduct()
                                                                                         .getCompany().getIdCompany());
                cost = subscription.getProductByIdProduct().getCost();
                if (customerWallet.getBalance() - cost >= 0) {
                    customerWallet.setBalance(customerWallet.getBalance() - cost);
                    subscription.setNextPayDate(nextPayDate);
                    companyWallet.setBalance(companyWallet.getBalance() + cost);
                    walletRepository.save(companyWallet);
                }
                else {
                    customer.setIsActive((byte) 0);
                    subscription.setIsActive((byte) 0);
                    customerWallet.setDebt(customerWallet.getDebt() + cost);
                }
                subscriptionRepository.save(subscription);
            }
            customer.setWalletByIdWallet(customerWallet);
            customerRepository.save(customer);
        }
    }
}

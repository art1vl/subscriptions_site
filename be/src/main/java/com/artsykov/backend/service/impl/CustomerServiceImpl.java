package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.entity.WalletEntity;
import com.artsykov.backend.model.CustomerPageModel;
import com.artsykov.backend.repository.CustomerRepository;
import com.artsykov.backend.service.CustomerService;
import com.artsykov.backend.service.SubscriptionService;
import com.artsykov.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository repository;
    private SubscriptionService subscriptionService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository,
                               SubscriptionService subscriptionService) {
        this.repository = repository;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public CustomerEntity findCustomerByLogInInfId(int logInInfId) {
        return repository.findByLogInInfIdLogInInf(logInInfId);
    }

    @Override
    public CustomerEntity findCustomerById(int customerId) {
        return repository.findByIdCustomer(customerId);
    }

    @Override
    public void updateCustomer(CustomerEntity customerEntity) {
        repository.save(customerEntity);
    }

    @Override
    public CustomerEntity saveCustomerWallet(CustomerEntity customerEntity) {
        return repository.save(customerEntity);
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return repository.save(customer);
    }

    @Override
    public CustomerPageModel findAllByPage(int pageNumber, int amount) {
        Pageable pageable = PageRequest.of(pageNumber, amount);
        Page<CustomerEntity> page = repository.findAll(pageable);
        CustomerPageModel customerPageModel = new CustomerPageModel();
        customerPageModel.setCustomerEntityList(page.getContent());
        customerPageModel.setTotalPages(page.getTotalPages());
        customerPageModel.setTotalElements(page.getTotalElements());
        return customerPageModel;
    }

    @Override
    public void changeStatus(CustomerEntity customerEntity) {
        repository.save(customerEntity);
        subscriptionService.changeSubscriptionStatusByCustomer(customerEntity);
    }

    @Override
    public CustomerEntity liquidateDebt(CustomerEntity customerEntity) {
        CustomerEntity customerFromDB = repository.findByIdCustomer(customerEntity.getIdCustomer());
        WalletEntity customerWallet = customerEntity.getWalletByIdWallet();
        if (customerEntity.getWalletByIdWallet().getDebt() <= 0) {
            customerEntity.setIsActive((byte) 1);
            customerWallet.setBalance(customerWallet.getBalance() + Math.abs(customerWallet.getDebt())
                                                                  + customerFromDB.getWalletByIdWallet().getDebt());
            customerWallet.setDebt(0);
            customerEntity.setWalletByIdWallet(customerWallet);
            subscriptionService.unblockSubscriptionsAfterCustomersUnblock(customerEntity);
        }
        else {
            customerWallet.setBalance(customerWallet.getBalance() + customerFromDB.getWalletByIdWallet().getDebt() -
                                                                    customerWallet.getDebt());
        }
        return repository.save(customerEntity);
    }
}

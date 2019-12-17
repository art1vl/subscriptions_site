package com.artsykov.backend.service.impl;

import com.artsykov.backend.entity.CustomerEntity;
import com.artsykov.backend.model.CustomerPageModel;
import com.artsykov.backend.repository.CustomerRepository;
import com.artsykov.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository repository;

//    @Autowired
//    public CustomerServiceImpl(CustomerRepository repository) {
//        this.repository = repository;
//    }

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
}

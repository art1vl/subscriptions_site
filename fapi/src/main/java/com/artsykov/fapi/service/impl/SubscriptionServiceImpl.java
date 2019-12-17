package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.SubscriptionConverter;
import com.artsykov.fapi.entity.SubscriptionEntity;
import com.artsykov.fapi.models.CustomerSubscriptionPageModel;
import com.artsykov.fapi.models.SubscriptionModel;
import com.artsykov.fapi.models.SubscriptionOrErrorsModel;
import com.artsykov.fapi.models.WalletModel;
import com.artsykov.fapi.service.CompanyDataService;
import com.artsykov.fapi.service.CustomerDataService;
import com.artsykov.fapi.service.SubscriptionService;
import com.artsykov.fapi.service.WalletDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    private SubscriptionConverter subscriptionConverter;
    private WalletDataService walletDataService;
    private CustomerDataService customerDataService;
    private CompanyDataService companyDataService;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionConverter subscriptionConverter,
                                   WalletDataService walletDataService,
                                   CustomerDataService customerDataService,
                                   CompanyDataService companyDataService) {
        this.subscriptionConverter = subscriptionConverter;
        this.walletDataService = walletDataService;
        this.customerDataService = customerDataService;
        this.companyDataService = companyDataService;
    }

    @Override
    public SubscriptionOrErrorsModel saveSubscription(SubscriptionModel subscriptionModel) {
        SubscriptionOrErrorsModel subscriptionOrErrorsModel = new SubscriptionOrErrorsModel();
        RestTemplate restTemplate = new RestTemplate();
        WalletModel customerWallet = customerDataService.findWalletByCustomerId(subscriptionModel.getIdCustomer());
        if (customerWallet != null && customerWallet.getBalance() - subscriptionModel.getProduct().getCost() >= 0) {
            SubscriptionEntity subscriptionEntity = subscriptionConverter.convertFromFrontToBack(subscriptionModel);
            subscriptionOrErrorsModel.setSubscriptionModel(
                    subscriptionConverter.convertFromBackToFront(restTemplate.
                        postForEntity(backendServerUrl + "/api/subscription", subscriptionEntity, SubscriptionEntity.class)
                            .getBody())
            );
            customerWallet.setBalance(customerWallet.getBalance() - subscriptionModel.getProduct().getCost());
            WalletModel companyWallet = companyDataService.findWalletByCompanyId(subscriptionModel.getProduct().getCompanyId());
            companyWallet.setBalance(companyWallet.getBalance() + subscriptionModel.getProduct().getCost());
            walletDataService.updateWallet(customerWallet);
            walletDataService.updateWallet(companyWallet);
        }
        else {
            Map<String, String> errors = new HashMap<>();
            errors.put("balance", "You don't have enough money for first month. Please, replenish your balance and then return");
            subscriptionOrErrorsModel.setErrors(errors);
        }
        return subscriptionOrErrorsModel;
    }

    @Override
    public SubscriptionModel findSubscription(int productId, int customerId) {
        RestTemplate restTemplate  = new RestTemplate();
        return subscriptionConverter.convertFromBackToFront(restTemplate.getForObject(backendServerUrl +
                "/api/subscription?product=" + productId + "&customer=" + customerId, SubscriptionEntity.class));
    }

    @Override
    public CustomerSubscriptionPageModel findAllSubscriptionsByCustomerId(int customerId, int page, int amount) {
        RestTemplate restTemplate = new RestTemplate();
        CustomerSubscriptionPageModel customerSubscriptionPageModel = restTemplate.getForObject(backendServerUrl +
                        "/api/subscription/customer/" + customerId + "?page=" + page + "&amount=" + amount,
                        CustomerSubscriptionPageModel.class);
        if (customerSubscriptionPageModel != null) {
            List<SubscriptionModel> subscriptionModels = customerSubscriptionPageModel.getSubscriptionEntities().stream()
                                                                .map(p -> subscriptionConverter.convertFromBackToFront(p))
                                                                .collect(Collectors.toList());
            customerSubscriptionPageModel.setSubscriptionModels(subscriptionModels);
            customerSubscriptionPageModel.setSubscriptionEntities(null);
        }
        return customerSubscriptionPageModel;
    }

    @Override
    public void deleteById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/subscription/" + id);
    }
}

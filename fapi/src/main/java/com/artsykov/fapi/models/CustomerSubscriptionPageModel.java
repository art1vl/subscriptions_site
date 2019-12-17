package com.artsykov.fapi.models;

import com.artsykov.fapi.entity.SubscriptionEntity;
import lombok.Data;

import java.util.List;

@Data
public class CustomerSubscriptionPageModel {
    int totalPages;
    long totalElements;
    List<SubscriptionEntity> subscriptionEntities;
    List<SubscriptionModel> subscriptionModels;
}

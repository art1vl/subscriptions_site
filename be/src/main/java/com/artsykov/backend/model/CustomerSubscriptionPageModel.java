package com.artsykov.backend.model;

import com.artsykov.backend.entity.SubscriptionEntity;
import lombok.Data;

import java.util.List;

@Data
public class CustomerSubscriptionPageModel {
    int totalPages;
    long totalElements;
    List<SubscriptionEntity> subscriptionEntities;
}

package com.artsykov.fapi.models;

import lombok.Data;

import java.util.Map;

@Data
public class SubscriptionOrErrorsModel {
    private SubscriptionModel subscriptionModel;
    private Map<String, String> errors;

    public SubscriptionOrErrorsModel() {}

    public SubscriptionOrErrorsModel(Map<String, String> errors) {
        this.errors = errors;
    }
}

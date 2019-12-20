package com.artsykov.fapi.models;

import com.artsykov.fapi.entity.CustomerEntity;
import lombok.Data;

import java.util.List;

@Data
public class CustomerPageModel {
    int totalPages;
    long totalElements;
    List<CustomerEntity> customerEntityList;
    List<CustomerModel> customerModelList;
}

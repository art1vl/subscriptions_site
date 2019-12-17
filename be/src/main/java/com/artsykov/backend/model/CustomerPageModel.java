package com.artsykov.backend.model;

import com.artsykov.backend.entity.CustomerEntity;
import lombok.Data;

import java.util.List;

@Data
public class CustomerPageModel {
    int totalPages;
    long totalElements;
    List<CustomerEntity> customerEntityList;
}

package com.artsykov.fapi.service;

import com.artsykov.fapi.models.ProductTypeModel;

import java.util.List;

public interface ProductTypeDataService {
    List<ProductTypeModel> findTypes();
}

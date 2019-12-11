package com.artsykov.fapi.service.impl;

import com.artsykov.fapi.converter.ProductTypeConverter;
import com.artsykov.fapi.entity.ProductTypeEntity;
import com.artsykov.fapi.models.ProductTypeModel;
import com.artsykov.fapi.service.ProductTypeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTypeDataServiceImpl implements ProductTypeDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    ProductTypeConverter productTypeConverter;

    @Override
    public List<ProductTypeModel> findTypes() {
        RestTemplate restTemplate = new RestTemplate();
        ProductTypeEntity[] productTypeEntities = restTemplate.getForObject(backendServerUrl + "/api/product/type/", ProductTypeEntity[].class);
        List<ProductTypeEntity> productTypeEntityList = new ArrayList<>(Arrays.asList(productTypeEntities));
        return productTypeEntityList.stream()
                                    .map(p -> productTypeConverter.convertFromBackToFront(p))
                                    .collect(Collectors.toList());
    }
}

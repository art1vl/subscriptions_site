package com.artsykov.fapi.models;

import com.artsykov.fapi.entity.CompanyEntity;
import lombok.Data;

import java.util.List;

@Data
public class CompanyPageModel {
    int totalPages;
    long totalElements;
    List<CompanyEntity> companyEntityList;
    List<CompanyModel> companyModelList;
}
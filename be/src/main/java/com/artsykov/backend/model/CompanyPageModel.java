package com.artsykov.backend.model;

import com.artsykov.backend.entity.CompanyEntity;
import lombok.Data;

import java.util.List;

@Data
public class CompanyPageModel {
    int totalPages;
    long totalElements;
    List<CompanyEntity> companyEntityList;
}

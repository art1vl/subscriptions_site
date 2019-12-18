package com.artsykov.fapi.models;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
public class ProductModel {
    @Min(value=0, message="Incorrect product id")
    private int id;

    @Min(value=1, message="incorrect company id")
    private int companyId;

    @NotEmpty(message = "Company name is required")
    @Pattern(regexp = "^[A-Z a-z0-9]+$",
            message = "Company name is invalid")
    private String companyName;

    @NotEmpty(message = "Description is required")
    @Pattern(regexp = "^[A-Z a-z0-9.,:;\\-\']+$",
            message = "Description is invalid")
    private String description;
    private String image;
    private ProductTypeModel type;
    private Date realiseDate;


    @Min(value=1, message="must be equal or greater than 1")
    private int cost;

    @NotEmpty(message = "Product name is required")
    @Pattern(regexp = "^[A-Z a-z0-9.,:;]+$",
            message = "Product name is invalid")
    private String productName;

    @Min(value=0, message="Incorrect product active status")
    @Max(value=1, message="Incorrect product active status")
    private int isActive;

    @Min(value=0, message="Incorrect company active status")
    @Max(value=1, message="Incorrect company active status")
    private int companyActiveStatus;

    public ProductModel() {}
}

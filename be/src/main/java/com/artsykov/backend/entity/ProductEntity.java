package com.artsykov.backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "backend", catalog = "")
public class ProductEntity {
    private int idProduct;
    private CompanyEntity company;
    private String description;
    private ProductTypeEntity type;
    private Date realiseDate;
    private int cost;
    private byte isActive;
    private String image;
    private String productName;

    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id_company", nullable = false)
    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id_product_type", nullable = false)
    public ProductTypeEntity getType() {
        return type;
    }

    public void setType(ProductTypeEntity type) {
        this.type = type;
    }

    @Basic
    @Column(name = "realise_date")
    public Date getRealiseDate() {
        return realiseDate;
    }

    public void setRealiseDate(Date realiseDate) {
        this.realiseDate = realiseDate;
    }

    @Basic
    @Column(name = "cost")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return idProduct == that.idProduct &&
                company == that.company &&
                type == that.type &&
                cost == that.cost &&
                isActive == that.isActive &&
                productName == that.productName &&
                Objects.equals(description, that.description) &&
                Objects.equals(realiseDate, that.realiseDate) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, company, description, type, realiseDate, cost, isActive, image, productName);
    }

//    @ManyToOne
//    @JoinColumn(name = "company", referencedColumnName = "id_company", nullable = false)
//    public CompanyEntity getCompanyByCompany() {
//        return companyByCompany;
//    }
//
//    public void setCompanyByCompany(CompanyEntity companyByCompany) {
//        this.companyByCompany = companyByCompany;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "type", referencedColumnName = "id_product_type", nullable = false)
//    public ProductTypeEntity getProductTypeByType() {
//        return productTypeByType;
//    }
//
//    public void setProductTypeByType(ProductTypeEntity productTypeByType) {
//        this.productTypeByType = productTypeByType;
//    }

//    @OneToMany(mappedBy = "productByIdProduct")
//    public Collection<SubscriptionEntity> getSubscriptionsByIdProduct() {
//        return subscriptionsByIdProduct;
//    }
//
//    public void setSubscriptionsByIdProduct(Collection<SubscriptionEntity> subscriptionsByIdProduct) {
//        this.subscriptionsByIdProduct = subscriptionsByIdProduct;
//    }
}

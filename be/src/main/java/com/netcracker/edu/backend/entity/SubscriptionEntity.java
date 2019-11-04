package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "subscription", schema = "backend", catalog = "")
public class SubscriptionEntity {
    private int idSubscription;
//    private int idProduct;
//    private int idCostumer;
    private Timestamp startSubscriptDate;
    private byte isActive;
    private ProductEntity productByIdProduct;
    private CustomerEntity customerByIdCostumer;

    @Id
    @Column(name = "id_subscription")
    public int getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(int idSubscription) {
        this.idSubscription = idSubscription;
    }

//    @Basic
//    @Column(name = "id_product")
//    public int getIdProduct() {
//        return idProduct;
//    }
//
//    public void setIdProduct(int idProduct) {
//        this.idProduct = idProduct;
//    }
//
//    @Basic
//    @Column(name = "id_costumer")
//    public int getIdCostumer() {
//        return idCostumer;
//    }
//
//    public void setIdCostumer(int idCostumer) {
//        this.idCostumer = idCostumer;
//    }

    @Basic
    @Column(name = "start_subscript_date")
    public Timestamp getStartSubscriptDate() {
        return startSubscriptDate;
    }

    public void setStartSubscriptDate(Timestamp startSubscriptDate) {
        this.startSubscriptDate = startSubscriptDate;
    }

    @Basic
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionEntity that = (SubscriptionEntity) o;
        return idSubscription == that.idSubscription &&
//                idProduct == that.idProduct &&
//                idCostumer == that.idCostumer &&
                isActive == that.isActive &&
                Objects.equals(startSubscriptDate, that.startSubscriptDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubscription, startSubscriptDate, isActive);
    }

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id_product", nullable = false)
    public ProductEntity getProductByIdProduct() {
        return productByIdProduct;
    }

    public void setProductByIdProduct(ProductEntity productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", nullable = false)
    public CustomerEntity getCustomerByIdCostumer() {
        return customerByIdCostumer;
    }

    public void setCustomerByIdCostumer(CustomerEntity costumerByIdCostumer) {
        this.customerByIdCostumer = costumerByIdCostumer;
    }
}

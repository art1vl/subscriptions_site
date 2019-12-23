package com.artsykov.backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "subscription", schema = "backend", catalog = "")
public class SubscriptionEntity {
    private int idSubscription;
    private java.sql.Date nextPayDate;
    private Date startSubscriptDate;
    private byte isActive;
    private ProductEntity productByIdProduct;
    private CustomerEntity customerByIdCustomer;

    @Id
    @Column(name = "id_subscription")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(int idSubscription) {
        this.idSubscription = idSubscription;
    }

    @Basic
    @Column(name = "start_subscript_date")
    public Date getStartSubscriptDate() {
        return startSubscriptDate;
    }

    public void setStartSubscriptDate(Date startSubscriptDate) {
        this.startSubscriptDate = startSubscriptDate;
    }

    @Basic
    @Column(name = "next_pay_date")
    public java.sql.Date getNextPayDate() {
        return nextPayDate;
    }

    public void setNextPayDate(java.sql.Date nextPayDate) {
        this.nextPayDate = nextPayDate;
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
    public CustomerEntity getCustomerByIdCustomer() {
        return customerByIdCustomer;
    }

    public void setCustomerByIdCustomer(CustomerEntity customerByIdCustomer) {
        this.customerByIdCustomer = customerByIdCustomer;
    }
}

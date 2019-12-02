package com.artsykov.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_type", schema = "backend", catalog = "")
public class ProductTypeEntity {
    private int idProductType;
    private String typeName;
//    private Collection<ProductEntity> productsByIdProductType;

    @Id
    @Column(name = "id_product_type")
    public int getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(int idProductType) {
        this.idProductType = idProductType;
    }

    @Basic
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductTypeEntity that = (ProductTypeEntity) o;
        return idProductType == that.idProductType &&
                Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductType, typeName);
    }

//    @OneToMany(mappedBy = "productTypeByType")
//    public Collection<ProductEntity> getProductsByIdProductType() {
//        return productsByIdProductType;
//    }
//
//    public void setProductsByIdProductType(Collection<ProductEntity> productsByIdProductType) {
//        this.productsByIdProductType = productsByIdProductType;
//    }
}

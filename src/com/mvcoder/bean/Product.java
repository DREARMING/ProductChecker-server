package com.mvcoder.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Product {
    private int pdType;
    private String pdName;
    private int creatorId;
    private String pdDescribe;
    private String pdImgUrl;

    @Basic
    @Column(name = "creatorId", nullable = false)
    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "pdType", nullable = false)
    public int getPdType() {
        return pdType;
    }

    public void setPdType(int pdType) {
        this.pdType = pdType;
    }

    @Basic
    @Column(name = "pdName", nullable = false, length = -1)
    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    @Basic
    @Column(name = "pdDescribe", nullable = true, length = -1)
    public String getPdDescribe() {
        return pdDescribe;
    }

    public void setPdDescribe(String pdDescribe) {
        this.pdDescribe = pdDescribe;
    }

    @Basic
    @Column(name = "pdImgUrl", nullable = true, length = -1)
    public String getPdImgUrl() {
        return pdImgUrl;
    }

    public void setPdImgUrl(String pdImgUrl) {
        this.pdImgUrl = pdImgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return pdType == product.pdType &&
                Objects.equals(pdName, product.pdName) &&
                Objects.equals(pdDescribe, product.pdDescribe) &&
                Objects.equals(pdImgUrl, product.pdImgUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pdType, pdName, pdDescribe, pdImgUrl);
    }
}

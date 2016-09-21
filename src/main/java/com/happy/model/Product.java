package com.happy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    public Product() {
    }

    @Id
    @Column(name = "storeId")
    private Integer storeId;

    @Column(name = "milkQty")
    private int milkQty;

    @Column(name = "eggQty")
    private int eggQty;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(int id) {
        this.storeId = id;
    }

    public int getMilkQty() {

        return milkQty;
    }

    public void setMilkQty(int milkQty) {
        this.milkQty = milkQty;
    }

    public int getEggQty() {
        return eggQty;
    }

    public void setEggQty(int eggQty) {
        this.eggQty = eggQty;
    }
}

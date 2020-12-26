package com.lumier.domain;

import java.util.Date;

public class ItemDetail {

    private Integer item_id;
    private String supplierName;
    private String supplierID;
    private String code;
    private String name;
    private String category;
    private Double unitCost;
    private Double unitPrice;
    private Date date;
    private Double newUnitCost;
    private Double newUnitPrice;

    public Integer getItem_id() {
        return this.item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierID() {
        return this.supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getUnitCost() {
        return this.unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getNewUnitCost() {
        return this.newUnitCost;
    }

    public void setNewUnitCost(Double newUnitCost) {
        this.newUnitCost = newUnitCost;
    }

    public Double getNewUnitPrice() {
        return this.newUnitPrice;
    }

    public void setNewUnitPrice(Double newUnitPrice) {
        this.newUnitPrice = newUnitPrice;
    }

}
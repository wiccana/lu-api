package com.lumier.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ospos_items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer item_id;

    private String item_number;
    private String name;
    private String category;
    private String supplier_id;
    private String description;
    private Double cost_price;
    private Double unit_price;

    public Item() {
    }

    public Item(Integer item_id, String item_number, String name, String category, String supplier_id,
            String description, Double cost_price, Double unit_price) {
        this.item_id = item_id;
        this.item_number = item_number;
        this.name = name;
        this.category = category;
        this.supplier_id = supplier_id;
        this.description = description;
        this.cost_price = cost_price;
        this.unit_price = unit_price;
    }

    public Integer getItem_id() {
        return this.item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getItem_number() {
        return this.item_number;
    }

    public void setItem_number(String item_number) {
        this.item_number = item_number;
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

    public String getSupplier_id() {
        return this.supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost_price() {
        return this.cost_price;
    }

    public void setCost_price(Double cost_price) {
        this.cost_price = cost_price;
    }

    public Double getUnit_price() {
        return this.unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "{" + " item_id='" + getItem_id() + "'" + ", item_number='" + getItem_number() + "'" + ", name='"
                + getName() + "'" + ", category='" + getCategory() + "'" + ", supplier_id='" + getSupplier_id() + "'"
                + ", description='" + getDescription() + "'" + ", cost_price='" + getCost_price() + "'"
                + ", unit_price='" + getUnit_price() + "'" + "}";
    }

}

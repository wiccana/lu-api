package com.lumier.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "ospos_receivings_items")
@IdClass(ReceivingItemId.class)
public class ReceivingItem {

    @Id
    private Integer receivingId;

    @Id
    private Integer itemId;

    @Id
    private Integer line;
    private String description;

    @Column(name = "quantity_purchased")
    private Double quantityPurchased;

    @Column(name = "item_cost_price")
    private Double itemCostPrice;

    @Column(name = "item_unit_price")
    private Double itemUnitPrice;

    @Column(name = "receiving_quantity")
    private Double receivingQuantity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(Double quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public Double getItemCostPrice() {
        return itemCostPrice;
    }

    public void setItemCostPrice(Double itemCostPrice) {
        this.itemCostPrice = itemCostPrice;
    }

    public Double getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(Double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public Double getReceivingQuantity() {
        return receivingQuantity;
    }

    public void setReceivingQuantity(Double receivingQuantity) {
        this.receivingQuantity = receivingQuantity;
    }

    public Integer getReceivingId() {
        return receivingId;
    }

    public void setReceivingId(Integer receivingId) {
        this.receivingId = receivingId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

}

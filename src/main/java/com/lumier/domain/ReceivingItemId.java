package com.lumier.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

public class ReceivingItemId implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "receiving_id")
    private Integer receivingId;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "line")
    private Integer line;

    public ReceivingItemId(Integer receivingId, Integer itemId, Integer line) {
        this.receivingId = receivingId;
        this.itemId = itemId;
        this.line = line;
    }

    public ReceivingItemId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ReceivingItemId))
            return false;
        ReceivingItemId that = (ReceivingItemId) o;
        return Objects.equals(getReceivingId(), that.getReceivingId()) && Objects.equals(getItemId(), that.getItemId())
                && Objects.equals(getLine(), that.getLine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReceivingId(), getItemId(), getLine());
    }
}
package edu.iu.c322.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class ReturnRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int orderId;
    private int itemId;
    private String reason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturnRequest that = (ReturnRequest) o;
        return id == that.id && orderId == that.orderId && itemId == that.itemId && reason.equals(that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, itemId, reason);
    }
}

package edu.iu.c322.orderservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty(message = "Item name cannot be empty")
    private String name;
    private int quantity;
    private int price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && quantity == item.quantity && price == item.price && name.equals(item.name) && order.equals(item.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price, order);
    }
}

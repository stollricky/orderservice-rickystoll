package edu.iu.c322.orderservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int customerId;
    private double total;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address shippingAddress;
    @Valid
    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    private LocalDate orderPlaced;

    public LocalDate getOrderPlaced() {
        return orderPlaced;
    }

    public void setOrderPlaced(LocalDate orderPlaced) {
        this.orderPlaced = orderPlaced;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && customerId == order.customerId && Double.compare(order.total, total) == 0 && shippingAddress.equals(order.shippingAddress) && items.equals(order.items) && payment.equals(order.payment) && orderPlaced.equals(order.orderPlaced);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, total, shippingAddress, items, payment, orderPlaced);
    }
}

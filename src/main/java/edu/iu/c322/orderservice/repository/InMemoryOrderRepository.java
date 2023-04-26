package edu.iu.c322.orderservice.repository;

import edu.iu.c322.orderservice.model.Item;
import edu.iu.c322.orderservice.model.Order;
import edu.iu.c322.orderservice.model.ReturnRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryOrderRepository {

    private List<Order> orders = new ArrayList<>();

    public List<Order> findAll() {
        return orders;
    }

    public int create(Order order) {
        int orderId = orders.size() + 1;
        order.setId(orderId);
//        order.setReturnRequests(new ArrayList<>());
        int itemId = 1;
        for (Item item : order.getItems()) {
            item.setId(itemId++);
        }
        orders.add(order);
        return orderId;
    }

    public void delete(int orderId) {
        Order o = getByOrderId(orderId);
        if (o != null) {
        } else {
            throw new IllegalStateException("Order id is not valid.");
        }
    }

//    public void update(ReturnRequest returnRequest) {
//        Order o = getByOrderId(returnRequest.getOrderId());
//        if (o != null) { //If the order exists in repository
//            Item i = getByItemId(o, returnRequest.getItemId());
//            if (i != null) {
//                if (o.getReturnRequests() == null) {
//                    o.setReturnRequests(new ArrayList<>());
//                }
//                o.getReturnRequests().add(returnRequest);
//            } else {
//                throw new IllegalStateException("Item id is not valid");
//            }
//        } else {
//            throw new IllegalStateException("Order id is not valid.");
//        }
//    }

    public List<Order> getByCustomerId (int id) {
        List<Order> orders = findByCustomerId(id);
        if (!orders.isEmpty()) {
            return orders;
        } else {
            throw new IllegalStateException("Orders not found with this id.");
        }
    }

    public Item getByItemId(Order order, int id) {
        return order.getItems().stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    public Order getByOrderId(int orderId) {
        return orders.stream().filter(x -> x.getId() == orderId).findAny().orElse(null);
    }

    public List<Order> findByCustomerId(int customerId) {
        return orders.stream().filter(x -> x.getCustomerId() == customerId).toList();
    }
}

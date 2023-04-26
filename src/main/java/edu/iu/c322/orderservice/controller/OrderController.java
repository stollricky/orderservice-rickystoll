package edu.iu.c322.orderservice.controller;

import edu.iu.c322.orderservice.model.Item;
import edu.iu.c322.orderservice.model.Order;
import edu.iu.c322.orderservice.model.ReturnRequest;
import edu.iu.c322.orderservice.model.UpdateRequest;
import edu.iu.c322.orderservice.repository.AddressRepository;
import edu.iu.c322.orderservice.repository.ItemRepository;
import edu.iu.c322.orderservice.repository.OrderRepository;
import edu.iu.c322.orderservice.repository.ReturnRequestRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private AddressRepository addressRepository;
    private ReturnRequestRepository returnRequestRepository;
    private final WebClient trackingService;

    public OrderController(OrderRepository orderRepository,
                           ItemRepository itemRepository,
                           AddressRepository addressRepository,
                           ReturnRequestRepository returnRequestRepository,
                           WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.addressRepository = addressRepository;
        this.returnRequestRepository = returnRequestRepository;
        this.trackingService = webClientBuilder.baseUrl("http://localhost:9003").build();
    }

    @GetMapping("/{id}")
    public List<Order> findByCustomerId(@PathVariable int id) {
        return orderRepository.findByCustomerId(id);
    }

    @GetMapping("/order/{id}")
    public Order findByOrderId(@PathVariable int id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @PostMapping
    public int create(@Valid @RequestBody Order order) {
        for (int i = 0; i < order.getItems().size(); ++i) {
            order.getItems().get(i).setOrder(order);
        }
        order.setOrderPlaced(LocalDate.now());
        Order o = orderRepository.save(order);
        addTrackings(o);
        return o.getId();
    }

    private void addTrackings (Order o) {
        for (int i = 0; i < o.getItems().size(); i++) {
            trackingService.put()
                    .uri("/trackings/{orderId}", o.getId())
                    .body(Mono.just(new UpdateRequest(o.getItems().get(i).getId(), "ordered.")), UpdateRequest.class)
                    .retrieve()
                    .bodyToMono(Void.class);
        }
    }

    @PutMapping("/return")
    public void update (@RequestBody ReturnRequest returnRequest) {
        returnRequestRepository.save(returnRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        Order order = new Order();
        order.setId(id);
        orderRepository.delete(order);
    }

    @DeleteMapping
    public void deleteAll() {
        orderRepository.deleteAll();
    }
}

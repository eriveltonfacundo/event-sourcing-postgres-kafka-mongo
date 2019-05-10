package com.example.eventsourcing.comand.controllers;

import com.example.eventsourcing.comand.entities.Order;
import com.example.eventsourcing.comand.entities.Payment;
import com.example.eventsourcing.comand.enums.OrderStatus;
import com.example.eventsourcing.comand.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Order model, UriComponentsBuilder uriBuilder) {
        Order order = orderService.findById(model.getId());
        if (order != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        model.prePersist();
        orderService.save(model);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/v1/orders/{id}").buildAndExpand(model.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/payments")
    public ResponseEntity<?> payment(@PathVariable("id") Long id, @RequestBody @Valid Payment payment) {
        Order order = orderService.checkPendingPayment(id);
        if (order == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        payment.prePersist();
        payment.setOrder(order);
        order.setPayment(payment);
        order.pay();
        orderService.payment(order, payment);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/refund")
    public ResponseEntity<?> refund(@PathVariable("id") Long id) {
        Order order = orderService.checkPossibleRefund(id);
        if (order == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        order.setStatus(OrderStatus.REFUNDED);
        orderService.save(order);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/refund/{itemId}")
    public ResponseEntity<?> refundItem(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId) {
        Order order = orderService.checkPossibleRefund(id);
        if (order == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        order.setStatus(OrderStatus.REFUNDED);
        orderService.save(order);

        return ResponseEntity.ok().build();
    }
}

package com.example.eventsourcing.comand.controllers;

import com.example.eventsourcing.comand.entities.Order;
import com.example.eventsourcing.comand.entities.Payment;
import com.example.eventsourcing.comand.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.example.eventsourcing.comand.enums.OrderStatus.REFUNDED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

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

        orderService.save(model);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/v1/orders/{id}").buildAndExpand(model.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/payments")
    public ResponseEntity<?> payment(@PathVariable("id") Long id, @RequestBody @Valid Payment payment) {
        Order order = orderService.checkPendingPayment(id);
        if (order == null)
            return new ResponseEntity<>(NOT_FOUND);

        orderService.payment(order, payment);

        return new ResponseEntity<>(NO_CONTENT);
    }

    @PostMapping("/{id}/refund")
    public ResponseEntity<?> refund(@PathVariable("id") Long id) {
        Order order = orderService.checkPossibleRefund(id);
        if (order == null)
            return new ResponseEntity<>(NOT_FOUND);

        order.setStatus(REFUNDED);
        orderService.save(order);

        return new ResponseEntity<>(NO_CONTENT);
    }

}

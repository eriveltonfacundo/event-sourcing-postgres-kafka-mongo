package com.example.eventsourcing.view.consumers;

import com.example.eventsourcing.view.entities.Order;
import com.example.eventsourcing.view.entities.Payment;
import com.example.eventsourcing.view.entities.Store;
import com.example.eventsourcing.view.repositories.OrderRepository;
import com.example.eventsourcing.view.repositories.PaymentRepository;
import com.example.eventsourcing.view.repositories.StoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaComponent {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "stores")
    public void stores(String message) throws IOException {
        storeRepository.save(objectMapper.readValue(message, Store.class));
    }

    @KafkaListener(topics = "payments")
    public void payments(String message) throws IOException {
        paymentRepository.save(objectMapper.readValue(message, Payment.class));
    }

    @KafkaListener(topics = "orders")
    public void orders(String message) throws IOException {
        orderRepository.save(objectMapper.readValue(message, Order.class));
    }
}

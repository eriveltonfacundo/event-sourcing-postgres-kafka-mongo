package com.example.eventsourcing.comand.services;

import com.example.eventsourcing.comand.entities.Order;
import com.example.eventsourcing.comand.entities.Payment;
import com.example.eventsourcing.comand.produces.KafkaComponent;
import com.example.eventsourcing.comand.repositories.OrderRepository;
import com.example.eventsourcing.comand.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private KafkaComponent kafkaComponent;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
        kafkaComponent.sendOrder(order);
    }

    @Cacheable(value = "order-sigle", key = "#id")
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public void payment(Order order, Payment payment) {
        payment.setOrder(order);
        order.setPayment(payment);
        order.pay();

        paymentRepository.save(payment);
        kafkaComponent.sendPayment(payment);
        orderRepository.save(order);
        kafkaComponent.sendOrder(order);

    }

    public Order checkPendingPayment(Long id) {
        return orderRepository.checkPendingPayment(id);
    }

    public Order checkPossibleRefund(Long id) {
        return orderRepository.checkPossibleRefund(id);
    }
}

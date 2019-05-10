package com.example.eventsourcing.comand.services;

import com.example.eventsourcing.comand.entities.Payment;
import com.example.eventsourcing.comand.produces.KafkaComponent;
import com.example.eventsourcing.comand.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private KafkaComponent kafkaComponent;
    @Autowired
    private PaymentRepository paymentRepository;

    public void save(Payment entity) {
        paymentRepository.save(entity);
        kafkaComponent.sendPayment(entity);
    }

    @Cacheable(value = "payment-sigle", key = "#id")
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
}

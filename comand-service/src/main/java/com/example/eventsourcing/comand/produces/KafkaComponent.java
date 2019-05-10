package com.example.eventsourcing.comand.produces;

import com.example.eventsourcing.comand.entities.Order;
import com.example.eventsourcing.comand.entities.Payment;
import com.example.eventsourcing.comand.entities.Store;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class KafkaComponent {
    private static final Logger logger = LoggerFactory.getLogger(KafkaComponent.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public void sendStore(Store store) {
        kafkaTemplate.send("stores", toJson(store));
    }

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public void sendOrder(Order entity) {
        kafkaTemplate.send("orders", toJson(entity));
    }

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public void sendPayment(Payment entity) {
        kafkaTemplate.send("payments", toJson(entity));
    }

    @Recover
    public String recover(Exception t) {
        return "Error Class :: " + t.getClass().getName();
    }

    private String toJson(Object message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}

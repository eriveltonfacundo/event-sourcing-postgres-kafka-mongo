package com.invillia.challenge.viewservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.challenge.viewservice.models.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private MongoTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "stores", groupId = "group_id")
    public void consume(String message) {
        template.save(toObject(message),"stores");
    }

    private Store toObject(String message) {
        try {
            return objectMapper.readValue(message, Store.class);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}

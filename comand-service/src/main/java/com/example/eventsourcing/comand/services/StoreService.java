package com.example.eventsourcing.comand.services;

import com.example.eventsourcing.comand.entities.Store;
import com.example.eventsourcing.comand.produces.KafkaComponent;
import com.example.eventsourcing.comand.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private KafkaComponent kafkaComponent;
    @Autowired
    private StoreRepository storeRepository;

    public Store save(Store entity) {
        kafkaComponent.sendStore(entity);
        return storeRepository.save(entity);
    }

    @Cacheable(value = "store-sigle", key = "#id")
    public Optional<Store> findById(Long id) {
        return storeRepository.findById(id);
    }
}

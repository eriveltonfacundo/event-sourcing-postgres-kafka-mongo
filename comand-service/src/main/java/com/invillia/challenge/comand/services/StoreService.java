package com.invillia.challenge.comand.services;

import com.invillia.challenge.comand.entities.Store;
import com.invillia.challenge.comand.produces.KafkaComponent;
import com.invillia.challenge.comand.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    @Autowired
    private KafkaComponent kafkaComponent;
    @Autowired
    private StoreRepository storeRepository;

    public void save(Store entity) {
        storeRepository.save(entity);
        kafkaComponent.sendStore(entity);
    }

    @Cacheable(value = "store-sigle", key = "#id")
    public Store findById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }
}

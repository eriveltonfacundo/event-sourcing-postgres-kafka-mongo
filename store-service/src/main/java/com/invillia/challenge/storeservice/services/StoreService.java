package com.invillia.challenge.storeservice.services;

import com.invillia.challenge.storeservice.entities.Store;
import com.invillia.challenge.storeservice.kafka.Producer;
import com.invillia.challenge.storeservice.mappers.TransformerMapper;
import com.invillia.challenge.storeservice.repositories.StoreRepository;
import com.invillia.challenge.storeservice.rsql.RSQLComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Service
public class StoreService {

    @Autowired
    private Producer producer;
    @Autowired
    private RSQLComponent rsqlComponent;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private TransformerMapper transformerMapper;

    public Page<Store> findAll(String filter, String sort, PageRequest pageRequest) {
        return storeRepository.findAll(rsqlComponent.toSpecification(filter), pageRequest);
    }

    public Store findById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public boolean exists(Long id) {
        return storeRepository.existsById(id);
    }

    public void save(Store store) {
        storeRepository.save(store);
        producer.sendMessage(transformerMapper.storeDTOtoStore(store));
    }

    public void update(Store store) {
        storeRepository.save(store);
    }

    public void deleteById(Long id) {
        storeRepository.deleteById(id);
    }
}

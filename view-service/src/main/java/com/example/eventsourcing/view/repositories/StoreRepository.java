package com.example.eventsourcing.view.repositories;

import com.example.eventsourcing.view.entities.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends MongoRepository<Store, String> {
}

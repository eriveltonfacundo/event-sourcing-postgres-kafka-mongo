package com.invillia.challenge.view.repositories;

import com.invillia.challenge.view.entities.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends MongoRepository<Store, String> {
}

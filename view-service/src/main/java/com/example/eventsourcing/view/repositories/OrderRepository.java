package com.example.eventsourcing.view.repositories;

import com.example.eventsourcing.view.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}

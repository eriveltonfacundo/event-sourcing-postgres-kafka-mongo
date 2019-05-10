package com.example.eventsourcing.view.repositories;

import com.example.eventsourcing.view.entities.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
}

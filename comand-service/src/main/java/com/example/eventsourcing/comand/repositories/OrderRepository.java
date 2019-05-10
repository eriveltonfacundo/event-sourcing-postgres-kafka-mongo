package com.example.eventsourcing.comand.repositories;

import com.example.eventsourcing.comand.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.status = 'PENDING' AND o.confirmationDate is null AND o.id = ?1 ")
    Order checkPendingPayment(Long id);

    @Query("SELECT o FROM Order o WHERE o.status = 'CONFIRMED' AND (cast(o.confirmationDate as date) - cast(now() as date)) <= 10 AND o.id = ?1 ")
    Order checkPossibleRefund(Long id);
}

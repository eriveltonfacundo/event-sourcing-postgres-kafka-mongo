package com.invillia.challenge.storeservice.services;

import com.invillia.challenge.storeservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


}

package com.example.eventsourcing.view.services;

import com.example.eventsourcing.view.entities.Payment;
import com.example.eventsourcing.view.entities.Store;
import com.example.eventsourcing.view.utils.PageResponse;
import com.example.eventsourcing.view.entities.Order;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewService {

    @Autowired
    private QueryService queryService;

    @GraphQLQuery(name = "stores")
    public PageResponse<Store> stores(@GraphQLArgument(name = "filter") String filter,
                                      @GraphQLArgument(name = "sort", defaultValue = "id") String sort,
                                      @GraphQLArgument(name = "page", defaultValue = "0") Integer page,
                                      @GraphQLArgument(name = "size", defaultValue = "100") Integer size) {
        return queryService.filter(filter, sort, page, size, Store.class);
    }

    @GraphQLQuery(name = "payments")
    public PageResponse<Payment> payments(@GraphQLArgument(name = "filter") String filter,
                                          @GraphQLArgument(name = "sort", defaultValue = "id") String sort,
                                          @GraphQLArgument(name = "page", defaultValue = "0") Integer page,
                                          @GraphQLArgument(name = "size", defaultValue = "100") Integer size) {
        return queryService.filter(filter, sort, page, size, Payment.class);
    }

    @GraphQLQuery(name = "orders")
    public PageResponse<Order> orders(@GraphQLArgument(name = "filter") String filter,
                                      @GraphQLArgument(name = "sort", defaultValue = "id") String sort,
                                      @GraphQLArgument(name = "page", defaultValue = "0") Integer page,
                                      @GraphQLArgument(name = "size", defaultValue = "100") Integer size) {
        return queryService.filter(filter, sort, page, size, Order.class);
    }

}

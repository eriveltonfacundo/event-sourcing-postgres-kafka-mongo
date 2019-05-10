package com.example.eventsourcing.view.controllers;

import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/graphql")
public class GraphQLController {

    @Autowired
    private GraphQL graphQL;

    @PostMapping
    public Map<String, Object> findById(@RequestBody String body) {
        return graphQL.execute(body).toSpecification();
    }
}

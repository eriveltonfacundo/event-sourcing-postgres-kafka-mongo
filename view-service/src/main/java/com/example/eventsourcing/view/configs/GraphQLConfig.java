package com.example.eventsourcing.view.configs;

import com.example.eventsourcing.view.services.ViewService;
import graphql.GraphQL;
import io.leangen.graphql.GraphQLSchemaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Autowired
    private ViewService viewService;

    @Bean
    GraphQL graphQL() {
        return GraphQL.newGraphQL(new GraphQLSchemaGenerator()
                .withOperationsFromSingleton(viewService)
                .generate()).build();
    }
}

package com.invillia.challenge.view.configs;

import com.invillia.challenge.view.services.ViewService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
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
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withOperationsFromSingleton(viewService)
                .generate();
        return GraphQL.newGraphQL(schema).build();
    }
}

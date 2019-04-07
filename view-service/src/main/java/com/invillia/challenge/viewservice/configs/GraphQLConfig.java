package com.invillia.challenge.viewservice.components;

import com.invillia.challenge.viewservice.controllers.PageResponse;
import com.invillia.challenge.viewservice.models.Store;
import com.invillia.challenge.viewservice.repositories.StoreReadRepository;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.geantyref.TypeToken;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.DefaultOperationBuilder;
import io.leangen.graphql.metadata.strategy.type.DefaultTypeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by eriveltonfacundo on 06/04/2019.
 */

@Component
public class GraphQLConfig {

    @Autowired
    private StoreReadRepository storeReadRepository;

    @Bean
    public GraphQL graphQL(){
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withBasePackages("com.invillia.challenge.viewservice.repositories")
                .withTypeTransformer(new DefaultTypeTransformer(true, true))
                .withOperationsFromSingleton(storeReadRepository, new TypeToken<StoreReadRepository<PageResponse<Store>>>(){}.getType())
                .withOperationBuilder(new DefaultOperationBuilder(DefaultOperationBuilder.TypeInference.LIMITED))
                .generate();
        return new GraphQL.Builder(schema).build();

    }
}

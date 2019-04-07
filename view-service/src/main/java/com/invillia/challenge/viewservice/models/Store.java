package com.invillia.challenge.viewservice.models;

import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by eriveltonfacundo on 06/04/2019.
 */

@Document(collection = "stores")
@GraphQLType
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Store {

    @Field
    private String id;

    private String street;

    private String name;

    private String numberStreet;

    private String complement;

    private String city;

    private String zipCode;
}

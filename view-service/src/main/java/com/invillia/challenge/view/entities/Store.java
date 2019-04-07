package com.invillia.challenge.view.entities;


import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@GraphQLType
@Getter @Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class Store implements Serializable {

    private String id;

    private String name;

    private String address;

}

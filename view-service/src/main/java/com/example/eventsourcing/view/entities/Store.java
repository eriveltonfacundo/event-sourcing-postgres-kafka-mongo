package com.example.eventsourcing.view.entities;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Getter @Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class Store implements Serializable {

    private String id;

    private String name;

    private String address;

}

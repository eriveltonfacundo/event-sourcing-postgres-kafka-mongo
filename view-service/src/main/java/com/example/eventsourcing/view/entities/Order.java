package com.example.eventsourcing.view.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Document
@Getter @Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class Order implements Serializable {

    private String id;

    private String address;

    private String status;

    private List<OrderItem> items;
}
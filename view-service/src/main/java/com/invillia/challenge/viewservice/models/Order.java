package com.invillia.challenge.viewservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by eriveltonfacundo on 06/04/2019.
 */

@Document(collection = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {

    @Id
    private Long id;

    private Date confirmationDate;

    private String status;

    private String street;

    private String numberStreet;

    private String complement;

    private String city;

    private String zipCode;

    private List<OrderItem> items;
}

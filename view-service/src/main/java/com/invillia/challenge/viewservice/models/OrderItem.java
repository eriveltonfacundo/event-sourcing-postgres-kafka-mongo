package com.invillia.challenge.viewservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Created by eriveltonfacundo on 06/04/2019.
 */

@Document(collection = "orders_items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderItem {

    @Id
    private Long id;

    private Long orderId;

    private String description;

    private BigDecimal unitPrice;

    private BigDecimal quantity;

}

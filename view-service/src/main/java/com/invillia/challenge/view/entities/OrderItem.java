package com.invillia.challenge.view.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Document
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderItem implements Serializable {

    private Long id;

    private String description;

    private BigDecimal unitPrice;

    private BigDecimal quantity;

    private String status;
}

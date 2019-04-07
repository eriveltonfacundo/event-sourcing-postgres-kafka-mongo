package com.invillia.challenge.viewservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by eriveltonfacundo on 06/04/2019.
 */

@Document(collection = "payments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {

    @Id
    private Long id;

    private Date paymentDate;

    private BigDecimal price;

    private String status;

    private String creditCardNumber;

}

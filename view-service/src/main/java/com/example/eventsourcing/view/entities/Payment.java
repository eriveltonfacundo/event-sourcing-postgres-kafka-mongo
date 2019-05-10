package com.example.eventsourcing.view.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Document
@Getter @Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class Payment implements Serializable {

    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentDate;

    private BigDecimal price;

    private String creditCardNumber;

}

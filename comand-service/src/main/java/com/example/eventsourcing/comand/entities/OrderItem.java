package com.example.eventsourcing.comand.entities;

import com.example.eventsourcing.comand.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter @Setter
@Entity @Table(name = "orders_items")
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotBlank @Size(min = 1, max = 100)
    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @NotNull @DecimalMin("0.01")
    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @NotNull @DecimalMin("0.001")
    @Column(name = "quantity", nullable = false, precision = 15, scale = 3)
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private OrderStatus status;
}

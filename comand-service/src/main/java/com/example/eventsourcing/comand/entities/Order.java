package com.example.eventsourcing.comand.entities;

import com.example.eventsourcing.comand.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Getter @Setter @Entity @Table(name = "orders")
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of = "id")
public class Order implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 1, max = 100)
    @Column(name = "address", nullable = false, unique = true, length = 100)
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "confirmation_date")
    private LocalDateTime confirmationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private OrderStatus status;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @NotNull
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderItem> items;

    public void prePersist() {
        this.setId(null);
        this.setStatus(OrderStatus.PENDING);
        ArrayList<OrderItem> items = new ArrayList<>(this.getItems());
        this.getItems().clear();
        for (OrderItem item : items) {
            item.setId(null);
            item.setOrder(this);
            item.setStatus(OrderStatus.CONFIRMED);
            this.getItems().add(item);
        }
    }

    public void pay() {
        this.setConfirmationDate(LocalDateTime.now());
        this.setStatus(OrderStatus.CONFIRMED);
    }
}
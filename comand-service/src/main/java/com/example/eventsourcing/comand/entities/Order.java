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
import java.util.List;
import java.util.stream.Collectors;

import static com.example.eventsourcing.comand.enums.OrderStatus.CONFIRMED;
import static com.example.eventsourcing.comand.enums.OrderStatus.PENDING;
import static java.time.LocalDateTime.now;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
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

    @PrePersist
    public void prePersist() {
        id = null;
        status = PENDING;
        items = items.stream().peek(item -> {
            item.setId(null);
            item.setOrder(this);
            item.setStatus(CONFIRMED);
        }).collect(Collectors.toList());
    }

    public void pay() {
        confirmationDate = now();
        status = CONFIRMED;
    }
}
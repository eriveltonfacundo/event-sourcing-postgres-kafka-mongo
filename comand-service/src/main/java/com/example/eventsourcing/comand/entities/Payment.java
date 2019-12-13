package com.example.eventsourcing.comand.entities;

import com.example.eventsourcing.comand.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.eventsourcing.comand.enums.PaymentStatus.*;
import static java.time.LocalDateTime.*;

@Getter @Setter @Entity @Table(name = "payments")
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of = "id")
public class Payment implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @NotNull @DecimalMin("0.01")
    @Column(name = "price", nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @NotBlank @Size(min = 16, max = 16)
    @Column(name = "credit_card_number", nullable = false, length = 16)
    private String creditCardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private PaymentStatus status;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @PrePersist
    public void prePersist() {
        id = null;
        paymentDate = now();
        status = CONFIRMED;
    }
}

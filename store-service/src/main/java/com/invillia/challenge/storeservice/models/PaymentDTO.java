package com.invillia.challenge.storeservice.models;

import com.invillia.challenge.storeservice.enums.PaymentStatus;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PaymentDTO {

    private Long id;

    @NotNull
    private Date paymentDate;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal price;

    @NotNull
    private PaymentStatus status;

    @NotEmpty
    private String creditCardNumber;

}

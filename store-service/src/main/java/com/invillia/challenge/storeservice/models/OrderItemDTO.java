package com.invillia.challenge.storeservice.models;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderItemDTO {

    private Long id;

    @NotNull
    private Long orderId;

    @NotBlank
    @Size(min = 1, max = 100)
    private String description;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal unitPrice;

    @NotNull
    @DecimalMin("0.001")
    private BigDecimal quantity;

}

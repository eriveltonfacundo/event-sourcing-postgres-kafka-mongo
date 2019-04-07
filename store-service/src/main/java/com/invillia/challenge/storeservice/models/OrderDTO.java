package com.invillia.challenge.storeservice.models;

import com.invillia.challenge.storeservice.enums.OrderStatus;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderDTO {

    private Long id;

    @NotNull
    private Date confirmationDate;

    @NotNull
    private OrderStatus status;

    @NotNull
    private AddressDTO address;

    @NotEmpty
    private List<OrderItemDTO> items;
}

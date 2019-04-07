package com.invillia.challenge.storeservice.models;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class StoreDTO {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    private AddressDTO address;
}

package com.invillia.challenge.storeservice.models;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"street", "numberStreet", "complement", "city", "zipCode"})
public class AddressDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String street;

    @NotBlank
    @Size(min = 1, max = 10)
    private String numberStreet;

    @NotBlank
    @Size(min = 1, max = 50)
    private String complement;

    @NotBlank
    @Size(min = 1, max = 50)
    private String city;

    @NotBlank
    @Size(min = 1, max = 10)
    private String zipCode;
}

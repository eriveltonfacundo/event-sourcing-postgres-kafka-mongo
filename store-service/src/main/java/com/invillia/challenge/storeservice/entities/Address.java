package com.invillia.challenge.storeservice.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"street", "numberStreet", "complement", "city", "zipCode"})
public class Address {

    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @NotBlank
    @Size(min = 1, max = 10)
    @Column(name = "number_street", nullable = false, length = 10)
    private String numberStreet;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "complement", nullable = false, length = 50)
    private String complement;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @NotBlank
    @Size(min = 1, max = 10)
    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

}

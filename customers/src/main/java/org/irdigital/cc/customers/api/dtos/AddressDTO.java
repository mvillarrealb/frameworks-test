package org.irdigital.cc.customers.api.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddressDTO {
    @NotNull
    @NotEmpty
    private String department;

    @NotNull
    @NotEmpty
    private String province;

    @NotNull
    @NotEmpty
    private String district;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}

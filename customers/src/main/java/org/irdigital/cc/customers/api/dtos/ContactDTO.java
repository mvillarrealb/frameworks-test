package org.irdigital.cc.customers.api.dtos;

import lombok.Builder;
import lombok.Data;
import org.irdigital.cc.customers.domain.enums.ContactType;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ContactDTO {
    @NotNull
    private ContactType contactType;

    @NotNull
    private String contact;

    private String contactTypeDetail;
}

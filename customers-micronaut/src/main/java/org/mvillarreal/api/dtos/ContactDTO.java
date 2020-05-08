package org.mvillarreal.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mvillarreal.domain.enums.ContactType;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    @NotNull
    private ContactType contactType;

    @NotNull
    private String contact;

    private String contactTypeDetail;
}

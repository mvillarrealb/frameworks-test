package org.irdigital.cc.customers.api.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.irdigital.cc.customers.domain.enums.DocumentType;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class CustomerDTO {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^([a-z]|[A-Z]|'|á|é|í|ó|ú|ñ|ü|Á|É|Í|Ó|Ú|Ñ|Ü|\\s|\\'|\\-)+$")
    private String name;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^([a-z]|[A-Z]|'|á|é|í|ó|ú|ñ|ü|Á|É|Í|Ó|Ú|Ñ|Ü|\\s|\\'|\\-)+$")
    private String lastName;

    @NotNull
    private DocumentType documentType;

    private String documentTypeDetail;

    @NotNull
    @NotEmpty
    private String identityDocument;

    @NotNull
    @Valid
    @Size(min=1)
    private List<AddressDTO> addresses;

    @NotNull
    @Valid
    @Size(min=1)
    private List<ContactDTO> contacts;

    @Singular
    private List<LinkDTO> links;
}

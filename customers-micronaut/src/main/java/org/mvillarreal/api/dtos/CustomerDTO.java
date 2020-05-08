package org.mvillarreal.api.dtos;

import io.micronaut.core.annotation.Introspected;
import lombok.*;
import org.mvillarreal.domain.enums.DocumentType;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Introspected
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

package org.irdigital.cc.customers.api.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinkDTO {
    private String href;
    private String verb;
    private String action;
}

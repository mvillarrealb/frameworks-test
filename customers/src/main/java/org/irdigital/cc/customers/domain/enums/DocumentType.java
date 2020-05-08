package org.irdigital.cc.customers.domain.enums;

import lombok.Getter;

import java.util.regex.Pattern;


public enum DocumentType {
    PASSPORT("Pasaporte", "^(?!^0+$)[a-zA-Z0-9]{3,20}$"),
    DNI("Documento Nacional de Identidad", "\\d{12}"),
    FOREIGN_DOCUMENT("Carne de Extranjería", "\\d{9}"),
    OTHER("Otros", null);

    DocumentType(String description, String regexp) {
        this.description = description;
        this.regexp = regexp;
    }

    public boolean validate(String value) {
        if(this.regexp == null) {
            return true;
        }
        return Pattern.matches(this.regexp, value);
    }
    @Getter
    private final String description;

    private final String regexp;
}

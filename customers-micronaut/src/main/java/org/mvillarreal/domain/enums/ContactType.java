package org.mvillarreal.domain.enums;

import lombok.Getter;

public enum ContactType {
    EMAIL("Correo electrónico","^.+@[^\\\"\\\\-].+$"),
    PHONE("Teléfono","/\\d{9}+/g"),
    WHATSAPP("Whatsapp",null);

    ContactType(String description, String regexp) {
        this.description = description;
        this.regexp = regexp;
    }

    public boolean validate(String value) {
        if(this.regexp == null) {
            return true;
        }
        return this.regexp.matches(value);
    }

    @Getter
    private final String description;

    private final String regexp;
}

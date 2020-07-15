package org.mvillarreal.domain.exception;


import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

public class NotFoundException extends HttpStatusException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND,message);
    }
}

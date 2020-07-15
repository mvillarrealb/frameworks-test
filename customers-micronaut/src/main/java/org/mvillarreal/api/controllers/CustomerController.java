package org.mvillarreal.api.controllers;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import org.mvillarreal.api.dtos.CustomerDTO;
import org.mvillarreal.api.services.ICustomerService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@Validated
public class CustomerController {

    private final ICustomerService customerService;

    @Status(HttpStatus.CREATED)
    @Post(value = "/customers", consumes = "application/json")
    public Single<CustomerDTO> createCustomer(@Body @Valid CustomerDTO customer) {
        return customerService.createCustomer(customer);
    }

    @Get("/customers")
    public Flowable<CustomerDTO> findCustomers() {
        return customerService.findCustomers();
    }

    @Get("/customers/{id}")
    public Maybe<CustomerDTO> findOne(@PathVariable("id") Long id) {
        return customerService.findOne(id);
    }

    @Status(HttpStatus.ACCEPTED)
    @Put(value="/customers/{id}", consumes = "application/json")
    public Single<CustomerDTO> update(@PathVariable("id")Long id, @Body @Valid CustomerDTO customer) {
        return customerService.update(id, customer);
    }

    @Status(HttpStatus.ACCEPTED)
    @Delete("/customers/{id}")
    public Completable delete(@PathVariable("id") Long id) {
        return customerService.delete(id);
    }
}

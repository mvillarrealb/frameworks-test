package org.mvillarreal.api.controllers;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import lombok.AllArgsConstructor;
import org.mvillarreal.api.dtos.CustomerDTO;
import org.mvillarreal.api.services.ICustomerService;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@Validated
public class CustomerController {

    private final ICustomerService customerService;

    @Status(HttpStatus.CREATED)
    @Post(value = "/customers", consumes = "application/json")
    public CustomerDTO createCustomer(@Body @Valid CustomerDTO customer) {
        return customerService.createCustomer(customer);
    }

    @Get("/customers")
    public List<CustomerDTO> findCustomers() {
        return customerService.findCustomers();
    }

    @Get("/customers/{id}")
    public CustomerDTO findOne(@PathVariable("id") Long id) {
        return customerService.findOne(id);
    }

    @Status(HttpStatus.ACCEPTED)
    @Put(value="/customers/{id}", consumes = "application/json")
    public CustomerDTO update(@PathVariable("id")Long id, @Body @Valid CustomerDTO customer) {
        return customerService.update(id, customer);
    }

    @Status(HttpStatus.ACCEPTED)
    @Delete("/customers/{id}")
    public void delete(@PathVariable("id") Long id) {
        customerService.delete(id);
    }
}

package org.irdigital.cc.customers.api.controllers;

import lombok.AllArgsConstructor;
import org.irdigital.cc.customers.api.dtos.CustomerDTO;
import org.irdigital.cc.customers.api.services.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/customers", consumes = "application/json")
    public CustomerDTO createCustomer(@RequestBody @Valid CustomerDTO customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/customers")
    public List<CustomerDTO> findCustomers() {
        return customerService.findCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO findOne(@PathVariable("id")Long id) {
        return customerService.findOne(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value="/customers/{id}", consumes = "application/json")
    public CustomerDTO update(@PathVariable("id")Long id, @RequestBody @Valid CustomerDTO customer) {
        return customerService.update(id, customer);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/customers/{id}")
    public void delete(@PathVariable("id") Long id) {
        customerService.delete(id);
    }
}

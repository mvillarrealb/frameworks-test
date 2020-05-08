package org.mvillarreal.api.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mvillarreal.api.dtos.CustomerDTO;
import org.mvillarreal.api.mappers.CustomerMapper;
import org.mvillarreal.api.services.ICustomerService;
import org.mvillarreal.domain.entity.Customer;
import org.mvillarreal.domain.exception.NotFoundException;
import org.mvillarreal.domain.repository.CustomerRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
@AllArgsConstructor
@Slf4j
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        log.info("Creating a customer");
        Customer customer = customerMapper.createDomain(customerDTO);
        customer.validate();
        customerRepository.save(customer);
        log.info("Customer successfully created");
        return customerMapper.createDTO(customer);
    }

    @Override
    public List<CustomerDTO> findCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::createDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findOne(Long id) {
        Customer customer = findCustomer(id);
        return customerMapper.createDTO(customer);
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO customerDTO) {
        Customer customer = findCustomer(id);
        customer.setName(customerDTO.getName());
        customer.setLastName(customerDTO.getLastName());
        customerRepository.save(customer);
        return customerMapper.createDTO(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = findCustomer(id);
        customerRepository.delete(customer);
    }

    private Customer findCustomer(Long id) {
        log.info("Finding a customer with id "+id);
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(!customerOptional.isPresent()) {
            log.warn("Customer with id "+id+ " Was not found will throw an exception");
            throw new NotFoundException("Customer not found");
        }
        log.info("Successfully returning client");
        return customerOptional.get();
    }
}

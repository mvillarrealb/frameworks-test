package org.mvillarreal.api.services.impl;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mvillarreal.api.dtos.CustomerDTO;
import org.mvillarreal.api.mappers.CustomerMapper;
import org.mvillarreal.api.services.ICustomerService;
import org.mvillarreal.domain.entity.Customer;
import org.mvillarreal.domain.repository.CustomerRepository;

import javax.inject.Singleton;

@Singleton
@AllArgsConstructor
@Slf4j
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public Single<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        log.info("Creating a customer");
        Customer customer = customerMapper.createDomain(customerDTO);
        customer.validate();
        return customerRepository
                .save(customer)
                .map(customerMapper::createDTO);
    }

    @Override
    public Flowable<CustomerDTO> findCustomers() {
        return customerRepository.findAll()
                .map(customerMapper::createDTO);
    }

    @Override
    public Maybe<CustomerDTO> findOne(Long id) {
        return findCustomer(id).map(customerMapper::createDTO);
    }

    @Override
    public Single<CustomerDTO> update(Long id, CustomerDTO customerDTO) {
        return findCustomer(id)
        .flatMapSingle(customer -> {
            customer.setName(customerDTO.getName());
            customer.setLastName(customerDTO.getLastName());
            return customerRepository.save(customer);
        })
        .map(customerMapper::createDTO);
    }

    @Override
    public Completable delete(Long id) {
       return findCustomer(id)
               .flatMapCompletable(customerRepository::delete);
    }

    private Maybe<Customer> findCustomer(Long id) {
        log.info("Finding a customer with id "+id);
        return customerRepository.findById(id);
    }
}

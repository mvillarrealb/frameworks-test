package org.mvillarreal;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mvillarreal.api.dtos.CustomerDTO;
import org.mvillarreal.api.mappers.CustomerMapper;
import org.mvillarreal.api.services.impl.CustomerService;
import org.mvillarreal.domain.entity.Customer;
import org.mvillarreal.domain.enums.DocumentType;
import org.mvillarreal.domain.exception.NotFoundException;
import org.mvillarreal.domain.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private final CustomerRepository customerRepository =  Mockito.mock(CustomerRepository.class);

    private CustomerService customerService;

    @BeforeEach
    void initUseCase() {
        customerService = new CustomerService(customerRepository, new CustomerMapper());
    }

    @Test
    public void createUser() {
        CustomerDTO customerDTO = CustomerDTO
                .builder()
                .name("JUAN")
                .lastName("FEARLESS")
                .contacts(Collections.emptyList())
                .addresses(Collections.emptyList())
                .documentType(DocumentType.FOREIGN_DOCUMENT)
                .identityDocument("001010210").build();
        Customer customer = Customer
                .builder()
                .name("JUAN")
                .lastName("FEARLESS")
                .documentType(DocumentType.FOREIGN_DOCUMENT)
                .identityDocument("001010210")
                .build();
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        CustomerDTO created = customerService.createCustomer(customerDTO);
        assertThat(created.getName()).isSameAs(customerDTO.getName());
        assertThat(created.getLastName()).isSameAs(customerDTO.getLastName());
        assertThat(created.getIdentityDocument()).isSameAs(customerDTO.getIdentityDocument());
    }

    @Test
    public void findUsers() {
        List<Customer> list = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(list);
        List<CustomerDTO> customersList = customerService.findCustomers();
        assertThat(customersList).hasSize(0);
    }

    @Test
    public void findUser() {
        Customer customer = Customer.builder().name("JUAN").lastName("FEARLESS").documentType(DocumentType.FOREIGN_DOCUMENT).identityDocument("0015659101").build();
        CustomerDTO customerDTO = CustomerDTO.builder().name("JUAN").lastName("FEARLESS").identityDocument("0015659101").build();
        when(customerRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(customer));
        CustomerDTO found = customerService.findOne(1L);
        assertThat(found.getName()).isSameAs(customerDTO.getName());
        assertThat(found.getLastName()).isSameAs(customerDTO.getLastName());
        assertThat(found.getIdentityDocument()).isSameAs(customerDTO.getIdentityDocument());
    }

    @Test
    public void handleNotFound() {
        when(customerRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThatThrownBy(() -> {
            customerService.findOne(1L);
        })
        .isInstanceOf(NotFoundException.class);
    }

    @Test
    public void deleteUser() {
        /*when(mockRepository.delete(Mockito.any(Customer.class))).then
        Assertions.assertThatCode(() -> customerService.delete(1L))
                  .doesNotThrowAnyException();*/
    }

}

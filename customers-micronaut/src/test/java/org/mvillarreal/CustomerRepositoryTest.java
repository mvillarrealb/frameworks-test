package org.mvillarreal;


import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.mvillarreal.domain.entity.Address;
import org.mvillarreal.domain.entity.Contact;
import org.mvillarreal.domain.entity.Customer;
import org.mvillarreal.domain.enums.ContactType;
import org.mvillarreal.domain.enums.DocumentType;
import org.mvillarreal.domain.repository.CustomerRepository;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class CustomerRepositoryTest {

    @Inject
    private CustomerRepository customerRepository;

    @Test
    public void repositoryIsNotNull(){
        assertThat(customerRepository).isNotNull();
    }

    @Test
    public void customerIsCreated() {
       /* Customer customer = giveMeCustomer();
        customer = customerRepository.save(customer);
        assertThat(customer.getId()).isNotNull();*/
    }

    @Test
    public void customerIsCreatedWithAddress() {
       /* Customer customer = giveMeCustomer();
        Address address   = giveMeAddress();
        customer.addAddress(address);
        customer = customerRepository.save(customer);
        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getAddresses()).hasSize(1);
        assertThat(customer.getContacts()).hasSize(0);*/
    }

    @Test
    public void customerIsCreatedWithContact() {
        /*Customer customer = giveMeCustomer();
        Contact contact   = giveMeContact();
        customer.addContact(contact);
        customer = customerRepository.save(customer);
        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getContacts()).hasSize(1);
        assertThat(customer.getAddresses()).hasSize(0);*/
    }
    /*
        @Test
        public void findCustomers() {
            List<Customer> customerList = customerRepository.findAll();
            assertThat(customerList).hasSize(10);
        }

        @Test
        public void findCustomer() {
            Optional<Customer> customer = customerRepository.findById(1L);
            assertThat(customer.isPresent()).isEqualTo(true);
            assertThat(customer.get()).isNotNull();
        }
    */
    private Contact giveMeContact() {
        return Contact.builder().contactType(ContactType.EMAIL).contact("jsmiedo@gmail.com").build();
    }

    private Address giveMeAddress() {
        return Address
                .builder()
                .address("AVENIDA JOSE PARDO 974")
                .latitude(-66.01)
                .longitude(10.00)
                .department("LIMA")
                .province("LIMA")
                .district("MIRAFLORES")
                .build();
    }

    private Customer giveMeCustomer() {
        return Customer
                .builder()
                .name("juan")
                .lastName("sin miedo")
                .identityDocument("001010650")
                .documentType(DocumentType.FOREIGN_DOCUMENT)
                .build();
    }
}

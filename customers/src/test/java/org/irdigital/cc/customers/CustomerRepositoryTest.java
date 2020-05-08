package org.irdigital.cc.customers;

import org.irdigital.cc.customers.domain.entity.Address;
import org.irdigital.cc.customers.domain.entity.Contact;
import org.irdigital.cc.customers.domain.entity.Customer;
import org.irdigital.cc.customers.domain.enums.ContactType;
import org.irdigital.cc.customers.domain.enums.DocumentType;
import org.irdigital.cc.customers.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
   "spring.datasource.url=jdbc:h2:mem:testdb",
   "spring.datasource.driverClassName=org.h2.Driver",
   "spring.datasource.username=sa",
   "spring.datasource.password=password",
   "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
   "spring.jpa.hibernate.ddl-auto=update"
})
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void repositoryIsNotNull(){
        assertThat(customerRepository).isNotNull();
    }

    @Test
    public void customerIsCreated() {
        Customer customer = giveMeCustomer();
        customer = customerRepository.save(customer);
        assertThat(customer.getId()).isNotNull();
    }

    @Test
    public void customerIsCreatedWithAddress() {
        Customer customer = giveMeCustomer();
        Address address   = giveMeAddress();
        customer.addAddress(address);
        customer = customerRepository.save(customer);
        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getAddresses()).hasSize(1);
        assertThat(customer.getContacts()).hasSize(0);
    }

    @Test
    public void customerIsCreatedWithContact() {
        Customer customer = giveMeCustomer();
        Contact contact   = giveMeContact();
        customer.addContact(contact);
        customer = customerRepository.save(customer);
        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getContacts()).hasSize(1);
        assertThat(customer.getAddresses()).hasSize(0);
    }

    @Test
    @Sql(statements = {
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (1, 'Corella', 'Tustin', 8018599, 'FOREIGN_DOCUMENT');",
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (2, 'Calv', 'Jerdan', 6744761, 'PASSPORT');",
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (3, 'Timi', 'Brett', 6743927, 'DNI');",
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (4, 'Opal', 'Whysall', 1003889, 'DNI');",
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (5, 'Karoly', 'Stanyland', 5293928, 'PASSPORT');",
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (6, 'Arvin', 'Ortas', 4539259, 'FOREIGN_DOCUMENT');",
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (7, 'Cherye', 'McMillian', 7410422, 'FOREIGN_DOCUMENT');",
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (8, 'Carlye', 'Rockwell', 4318190, 'FOREIGN_DOCUMENT');",
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (9, 'Reuven', 'Gilding', 7235242, 'PASSPORT');",
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (10, 'Anatol', 'Kordovani', 7662817, 'FOREIGN_DOCUMENT');"
    })
    public void findCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(10);
    }

    @Test
    @Sql(statements = {
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (1, 'Corella', 'Tustin', 8018599, 'FOREIGN_DOCUMENT');",
            "insert into customers (customer_id, name, last_name, identity_document, document_type) values (2, 'Calv', 'Jerdan', 6744761, 'PASSPORT');"
    })
    public void findCustomer() {
        Optional<Customer> customer = customerRepository.findById(1L);
        assertThat(customer.isPresent()).isEqualTo(true);
        assertThat(customer.get()).isNotNull();
    }

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

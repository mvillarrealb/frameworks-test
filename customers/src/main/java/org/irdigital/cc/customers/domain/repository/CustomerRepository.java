package org.irdigital.cc.customers.domain.repository;

import org.irdigital.cc.customers.domain.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @EntityGraph(attributePaths = {"addresses", "contacts"})
    Optional<Customer> findById(Long id);

    @EntityGraph(attributePaths = {"addresses", "contacts"})
    List<Customer> findAll();
}

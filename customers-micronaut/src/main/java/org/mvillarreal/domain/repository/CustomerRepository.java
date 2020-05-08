package org.mvillarreal.domain.repository;


import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.annotation.EntityGraph;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.mvillarreal.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @EntityGraph(attributePaths = {"addresses", "contacts"})
    Optional<Customer> findById(Long id);

    @Join(value = "addresses",type = Join.Type.LEFT_FETCH)
    @Join(value = "contacts",type = Join.Type.LEFT_FETCH)
    List<Customer> findAll();
}

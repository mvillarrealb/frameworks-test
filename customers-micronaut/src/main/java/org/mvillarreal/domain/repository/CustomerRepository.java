package org.mvillarreal.domain.repository;


import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.annotation.EntityGraph;
import io.micronaut.data.repository.reactive.RxJavaCrudRepository;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.mvillarreal.domain.entity.Customer;

@Repository
public interface CustomerRepository extends RxJavaCrudRepository<Customer, Long> {
    @EntityGraph(attributePaths = {"addresses", "contacts"})
    Maybe<Customer> findById(Long id);

    @Join(value = "addresses",type = Join.Type.LEFT_FETCH)
    @Join(value = "contacts",type = Join.Type.LEFT_FETCH)
    Flowable<Customer> findAll();
}

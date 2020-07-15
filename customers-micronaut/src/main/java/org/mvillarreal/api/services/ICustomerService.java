package org.mvillarreal.api.services;


import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.mvillarreal.api.dtos.CustomerDTO;

public interface ICustomerService {

    Single<CustomerDTO> createCustomer(CustomerDTO customer);

    Flowable<CustomerDTO> findCustomers();

    Maybe<CustomerDTO> findOne(Long id);

    Single<CustomerDTO> update(Long id, CustomerDTO customer);

    Completable delete(Long id);
}

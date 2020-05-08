package org.mvillarreal.api.services;


import org.mvillarreal.api.dtos.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    CustomerDTO createCustomer(CustomerDTO customer);

    List<CustomerDTO> findCustomers();

    CustomerDTO findOne(Long id);

    CustomerDTO update(Long id, CustomerDTO customer);

    void delete(Long id);
}

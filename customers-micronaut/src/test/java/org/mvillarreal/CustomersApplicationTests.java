package org.mvillarreal;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mvillarreal.api.dtos.CustomerDTO;
import org.mvillarreal.domain.entity.Customer;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@MicronautTest
public class CustomersApplicationTests {
    @Inject
    @Client("/customers")
    RxHttpClient client;

    @Inject
    private ObjectMapper mapper;

    private List<CustomerDTO> customerDTOList;

    private List<Customer> customerList;

    @BeforeEach
    public void contextLoads() throws IOException {
        CustomerDTO[] customerDTOS = mapper.readValue(new File("src/test/resources/customer_dtos.json"), CustomerDTO[].class);
        Customer[] customers       = mapper.readValue(new File("src/test/resources/customers.json"), Customer[].class);
        customerDTOList = Arrays.asList(customerDTOS);
        customerList = Arrays.asList(customers);
    }

    @Test
    public void createCustomer() throws Exception {
        CustomerDTO customerDTO = customerDTOList.get(0);
        //when(customerService.createCustomer(Mockito.any(CustomerDTO.class))).thenReturn(customerDTO);
/*
        CustomerDTO results = client
                .exchange(HttpRequest.POST("/", customerDTO), Argument.listOf(CustomerDTO.class))
                .blockingFirst();
        mvc.perform(
                post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(customerDTO))
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is(customerDTO.getName())))
        .andExpect(jsonPath("$.lastName", is(customerDTO.getLastName())))
        .andExpect(jsonPath("$.documentType", is(String.valueOf(customerDTO.getDocumentType()))))
        .andExpect(jsonPath("$.identityDocument", is(customerDTO.getIdentityDocument())));*/
    }
}

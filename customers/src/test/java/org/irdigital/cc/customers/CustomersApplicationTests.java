package org.irdigital.cc.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.irdigital.cc.customers.api.controllers.CustomerController;
import org.irdigital.cc.customers.api.dtos.CustomerDTO;
import org.irdigital.cc.customers.api.services.impl.CustomerService;
import org.irdigital.cc.customers.domain.entity.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CustomerController.class})
class CustomersApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private CustomerService customerService;

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
		when(customerService.createCustomer(Mockito.any(CustomerDTO.class))).thenReturn(customerDTO);
		mvc.perform(
			post("/customers")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsString(customerDTO))
		)
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name", is(customerDTO.getName())))
		.andExpect(jsonPath("$.lastName", is(customerDTO.getLastName())))
		.andExpect(jsonPath("$.documentType", is(String.valueOf(customerDTO.getDocumentType()))))
		.andExpect(jsonPath("$.identityDocument", is(customerDTO.getIdentityDocument())));
	}

	@Test
	public void findCustomers() throws Exception {
		CustomerDTO customer = customerDTOList.get(0);
		when(customerService.findCustomers()).thenReturn(customerDTOList);
		mvc.perform(
			get("/customers")
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(5)))
		.andExpect(jsonPath("$[0].name", is(customer.getName())))
		.andExpect(jsonPath("$[0].lastName", is(customer.getLastName())))
		.andExpect(jsonPath("$[0].documentType", is(String.valueOf(customer.getDocumentType()))))
		.andExpect(jsonPath("$[0].identityDocument", is(customer.getIdentityDocument())));
	}

	@Test
	public void findCustomer() throws Exception {
		CustomerDTO customer = customerDTOList.get(0);
		when(customerService.findOne(Mockito.anyLong())).thenReturn(customer);
		mvc.perform(
			get("/customers/1")
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is(customer.getName())))
		.andExpect(jsonPath("$.lastName", is(customer.getLastName())))
		.andExpect(jsonPath("$.documentType", is(String.valueOf(customer.getDocumentType()))))
		.andExpect(jsonPath("$.identityDocument", is(customer.getIdentityDocument())));
	}

	@Test
	public void updateCustomer() throws Exception {
		CustomerDTO customer = customerDTOList.get(0);
		when(customerService.update(Mockito.anyLong(), Mockito.any(CustomerDTO.class))).thenReturn(customer);
		mvc.perform(
			put("/customers/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsString(customer))
		)
		.andExpect(status().isAccepted())
		.andExpect(jsonPath("$.name", is(customer.getName())))
		.andExpect(jsonPath("$.lastName", is(customer.getLastName())))
		.andExpect(jsonPath("$.documentType", is(String.valueOf(customer.getDocumentType()))))
		.andExpect(jsonPath("$.identityDocument", is(customer.getIdentityDocument())));
	}

	@Test
	public void deleteCustomer() throws Exception {
		CustomerDTO customer = customerDTOList.get(0);
		when(customerService.findOne(Mockito.anyLong())).thenReturn(customer);
		mvc.perform(
			delete("/customers/1").contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isAccepted());
	}

}

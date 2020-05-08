package org.mvillarreal.api.mappers;

import org.mvillarreal.api.dtos.AddressDTO;
import org.mvillarreal.api.dtos.ContactDTO;
import org.mvillarreal.api.dtos.CustomerDTO;
import org.mvillarreal.api.dtos.LinkDTO;
import org.mvillarreal.domain.entity.Address;
import org.mvillarreal.domain.entity.Contact;
import org.mvillarreal.domain.entity.Customer;

import javax.inject.Singleton;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class CustomerMapper {

    public CustomerDTO createDTO(Customer customer) {
        List<AddressDTO> addressDTOList = customer
                .getAddresses()
                .stream()
                .map(this::createAddressDTO)
                .collect(Collectors.toList());
        List<ContactDTO> contactDTOList = customer
                .getContacts()
                .stream()
                .map(this::createContactDTO)
                .collect(Collectors.toList());
        String resourceId = "/customers/" + customer.getId();
        return CustomerDTO
                .builder()
                .name(customer.getName())
                .lastName(customer.getLastName())
                .identityDocument(customer.getIdentityDocument())
                .documentType(customer.getDocumentType())
                .documentTypeDetail(customer.getDocumentType().getDescription())
                .addresses(addressDTOList)
                .contacts(contactDTOList)
                .link(LinkDTO.builder().href(resourceId).verb("get").action("findOne").build())
                .link(LinkDTO.builder().href(resourceId).verb("put").action("updateCustomer").build())
                .link(LinkDTO.builder().href(resourceId).verb("delete").action("deleteCustomer").build())
                .build();
    }

    public Customer createDomain(CustomerDTO customerDTO) {
        Set<Address> addressDTOList = customerDTO
                .getAddresses()
                .stream()
                .map(this::createAddress).collect(Collectors.toSet());
        Set<Contact> contactDTOList = customerDTO
                .getContacts()
                .stream()
                .map(this::createContact).collect(Collectors.toSet());
        Customer customer = Customer
                .builder()
                .name(customerDTO.getName().toUpperCase())
                .lastName(customerDTO.getLastName().toUpperCase())
                .identityDocument(customerDTO.getIdentityDocument().toUpperCase())
                .documentType(customerDTO.getDocumentType())
                .build();
        addressDTOList.forEach(customer::addAddress);
        contactDTOList.forEach(customer::addContact);
        return customer;
    }

    private ContactDTO createContactDTO(Contact contact) {
        return ContactDTO
                .builder()
                .contact(contact.getContact())
                .contactTypeDetail(contact.getContactType().getDescription())
                .contactType(contact.getContactType())
                .build();
    }

    private AddressDTO createAddressDTO(Address address) {
        return AddressDTO
                .builder()
                .address(address.getAddress())
                .department(address.getDepartment())
                .province(address.getProvince())
                .district(address.getDistrict())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .build();
    }

    private Contact createContact(ContactDTO contactDTO) {
        return Contact.builder()
                .contact(contactDTO.getContact())
                .contactType(contactDTO.getContactType())
                .build();
    }

    private Address createAddress(AddressDTO addressDTO) {
        return Address
                .builder()
                .address(addressDTO.getAddress())
                .department(addressDTO.getDepartment())
                .province(addressDTO.getProvince())
                .district(addressDTO.getDistrict())
                .latitude(addressDTO.getLatitude())
                .longitude(addressDTO.getLongitude())
                .build();
    }
}

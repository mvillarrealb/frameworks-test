import { Injectable, NotFoundException } from '@nestjs/common';
import { Repository } from 'typeorm';
import { Customer } from './entity/customer.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { CustomerDTO } from './dto/customer.dto';
import { AddressDTO } from './dto/address.dto';
import { ContactDTO } from './dto/contact.dto';
import { Address } from './entity/address.entity';
import { Contact } from './entity/contact.entity';

@Injectable()
export class CustomersService {
  constructor(
    @InjectRepository(Customer)
    private readonly repository: Repository<Customer>,
  ) {}
  async createCustomer(customerDTO: CustomerDTO): Promise<CustomerDTO> {
    console.log(customerDTO);
    const created = await this.repository.save(customerDTO);
    return this.maptoDTO(created);
  }
  
  async findCustomers(): Promise<CustomerDTO[]> {
    const customers = await this.repository
      .find({
        relations: ['contacts', 'addresses']
      });
    return customers.map(this.maptoDTO.bind(this));
  }
  
  async findOne(id: number): Promise<CustomerDTO> {
    const customer = await this.findCustomer(id);
    return this.maptoDTO(customer);
  }

  async update(id: number, customerDTO: CustomerDTO): Promise<CustomerDTO> {
    await this.findCustomer(id);
    this.repository.update({ id }, customerDTO)
    return null;
  }

  async delete(id: number): Promise<void> {
    const customer = await this.findCustomer(id);
    await this.repository.delete(customer);
  }
  
  private async findCustomer(id: number): Promise<Customer> {
    const customer = await this.repository.findOne(id);
    if(customer == null) {
      throw new NotFoundException('');
    }
    return customer;
  }
  maptoDTO(customer: Customer): CustomerDTO {
    const href = `/customers/${customer.id}`;
    const { name, lastName, identityDocument, documentType } = customer;
    const addresses = customer.addresses.map(this.addressToDTO);
    const contacts = customer.contacts.map(this.contactToDTO);
    const dto = Object.assign(new CustomerDTO(), { name, lastName, identityDocument, documentType, addresses, contacts })
    dto.addLink({ href, verb: 'get', action: 'getCustomer' });
    dto.addLink({ href, verb: 'put', action: 'updateCustomer' });
    dto.addLink({ href, verb: 'delete', action: 'deleteCustomer' });
    return dto;
  }
  
  addressToDTO(addressEntity: Address): AddressDTO {
    const { 
      department,
      province,
      district,
      address,
      latitude,
      longitude
    } = addressEntity;
    return { department,
      province,
      district,
      address,
      latitude,
      longitude
    };
  }

  contactToDTO(contactEntity: Contact): ContactDTO {
    const { contact, contactType} = contactEntity;
    return { contact, contactType};
  }
}

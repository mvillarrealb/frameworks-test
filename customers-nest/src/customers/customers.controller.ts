import { Controller, Post, Get, Put, Delete, Body, Param, ParseIntPipe, ValidationPipe } from '@nestjs/common';
import { CustomersService } from './customers.service';
import { CustomerDTO } from './dto/customer.dto';

@Controller('customers')
export class CustomersController {
  constructor(
    private readonly customerService: CustomersService,
  ) {}
  @Post()
  public createCustomer(@Body(ValidationPipe) customer: CustomerDTO): Promise<CustomerDTO> {
    return this.customerService.createCustomer(customer);
  }

  @Get()
  public findCustomers(): Promise<CustomerDTO[]> {
    return this.customerService.findCustomers();
  }

  @Get(":id")
  public findOne(@Param("id", ParseIntPipe) id: number): Promise<CustomerDTO> {
    return this.customerService.findOne(id);
  }

  @Put(":id")
  public  update(
    @Param("id", ParseIntPipe)id: number, 
    @Body(ValidationPipe) customer: CustomerDTO
  ): Promise<CustomerDTO> {
    return this.customerService.update(id, customer);
  }

  @Delete(":id")
  public delete(@Param("id", ParseIntPipe) id: number): Promise<void> {
    return this.customerService.delete(id);
  }
}

import { BaseEntity, Entity, Column, PrimaryGeneratedColumn, ManyToOne } from 'typeorm';
import { Customer } from './customer.entity';

@Entity()
export class Address extends BaseEntity {
  
  @PrimaryGeneratedColumn()
  id: number;
  
  @Column()
  department: string;
  
  @Column()
  province: string;
  
  @Column()
  district: string;
  
  @Column()
  address: string;
  
  @Column()
  latitude: number;
  
  @Column()
  longitude: number;

  @ManyToOne(type => Customer, customer => customer.contacts, { eager: false })
  customer: Customer;
}

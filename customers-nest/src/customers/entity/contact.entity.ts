import { BaseEntity, Entity, Column, PrimaryGeneratedColumn, ManyToOne } from 'typeorm';
import { Customer } from './customer.entity';

@Entity()
export class Contact extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;
  
  @Column()
  contactType: string;
  
  @Column()
  contact: string;

  @ManyToOne(type => Customer, customer => customer.contacts, { eager: false })
  customer: Customer;
}
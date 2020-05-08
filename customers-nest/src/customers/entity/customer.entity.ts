import { BaseEntity, Entity, PrimaryGeneratedColumn, Column, OneToMany } from 'typeorm';
import { Contact } from './contact.entity';
import { Address } from './address.entity';

@Entity()
export class Customer extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;
  
  @Column()
  name: string;
  
  @Column()
  lastName: string;
  
  @Column()
  identityDocument: string;
  
  @Column()
  documentType: string;
  
  @OneToMany(type => Contact, contact => contact.customer, { eager: true, cascade: true })
  contacts: Contact[];

  @OneToMany(type => Address, address => address.customer, { eager: true, cascade: true })
  addresses: Address[];
}

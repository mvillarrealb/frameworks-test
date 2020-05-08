import { IsNotEmpty, ArrayNotEmpty, ValidateNested } from "class-validator";
import { Type } from 'class-transformer';
import { ContactDTO } from './contact.dto'
import { AddressDTO } from './address.dto';
import { LinkDTO } from './link.dto';

export class CustomerDTO {
  @IsNotEmpty()
  name: string;
  
  @IsNotEmpty()
  lastName: string;
  
  @IsNotEmpty()
  identityDocument: string;
  
  @IsNotEmpty()
  documentType: string;

  @IsNotEmpty()
  @ArrayNotEmpty()
  @ValidateNested({ each: true})
  @Type(() => ContactDTO)
  contacts: ContactDTO[];

  @IsNotEmpty()
  @ArrayNotEmpty()
  @ValidateNested({ each: true})
  @Type(() => AddressDTO)
  addresses: AddressDTO[];

  links: LinkDTO[] = [];
  addLink(link: LinkDTO) {
    this.links.push(link);
  }
}
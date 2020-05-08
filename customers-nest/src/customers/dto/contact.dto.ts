import { IsNotEmpty, IsEnum } from "class-validator";

export class ContactDTO {
  //@IsEnum(StreetType, {message: `streetType mus be one of: ${Object.keys(StreetType).join(',')}`})
  @IsNotEmpty()
  contactType: string;
  
  @IsNotEmpty()
  contact: string;
}

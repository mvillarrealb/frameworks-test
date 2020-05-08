import { IsNotEmpty, IsLongitude, IsLatitude } from "class-validator";

export class AddressDTO {
  @IsNotEmpty()
  department: string;
  
  @IsNotEmpty()
  province: string;
  
  @IsNotEmpty()
  district: string;
  
  @IsNotEmpty()
  address: string;
  
  @IsNotEmpty()
  @IsLatitude()
  latitude: number;

  @IsNotEmpty()
  @IsLongitude()
  longitude: number;
}

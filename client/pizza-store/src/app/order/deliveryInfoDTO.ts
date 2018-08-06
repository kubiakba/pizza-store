import {AddressDTO} from "./addressDTO";
import {TelephoneDTO} from "./telephoneDTO";

export class DeliveryInfoDTO {

  name :String;
  surname :String;
  addressDTO :AddressDTO;
  telephoneDTO: TelephoneDTO;

  constructor(data:any) {
    this.name = data.name;
    this.surname = data.surname;
    this.addressDTO = new AddressDTO(data.address);
    this.telephoneDTO = new TelephoneDTO(data.telephone);
  }
}

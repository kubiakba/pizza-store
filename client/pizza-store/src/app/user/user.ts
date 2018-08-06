import {AddressDTO} from "../order/addressDTO";
import {TelephoneDTO} from "../order/telephoneDTO";

export class User {

  email :String;
  name :String;
  surname :String;
  address :AddressDTO;
  telephone :TelephoneDTO;

  constructor(data:any) {
    this.email = data.email;
    this.name = data.name;
    this.surname = data.surname;
    this.address = new AddressDTO(data.address);
    this.telephone = new TelephoneDTO(data.telephone);
  }
}

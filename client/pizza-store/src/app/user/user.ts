import {Address} from "./address";
import {Telephone} from "./telephone";

export class User {

  email :String;
  name :String;
  surname :String;
  address :Address;
  telephone :Telephone;

  constructor(data:any) {
    this.email = data.email;
    this.name = data.name;
    this.surname = data.surname;
    this.address = new Address(data.address);
    this.telephone = new Telephone(data.telephone);
  }
}

import {Address} from "./address";
import {Telephone} from "./telephone";

export class User {

  email :String;
  name :String;
  password :String;
  surname :String;
  address :Address;
  telephone :Telephone;

  constructor(data:any) {
    this.email = data.email;
    this.name = data.name;
    this.password = data.password;
    this.surname = data.surname;
    this.address = data.address;
    this.telephone = data.telephone;
  }
}

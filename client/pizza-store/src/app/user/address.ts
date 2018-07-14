export class Address {
  city: String;
  street: String;
  streetNumber: String;
  postCode: String;

  constructor(city: String, street: String, streetNumber: String, postCode: String) {
    this.city = city;
    this.street = street;
    this.streetNumber = streetNumber;
    this.postCode = postCode;
  }

}

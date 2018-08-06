export class AddressDTO {
  city: String;
  street: String;
  streetNumber: String;
  postCode: String;

  constructor(data:any) {
    this.city = data.city;
    this.street = data.street;
    this.streetNumber = data.streetNumber;
    this.postCode = data.postCode;
  }

}

export class Kebab {

  price:String;
  description: String;
  name: String;

  constructor(data:any) {
    this.price = data.price;
    this.description = data.description;
    this.name = data.name;
  }
}

export class PizzaTopping {

  price:String;
  name: String;

  constructor(data:any) {
    this.price = data.price;
    this.name = data.name;
  }
}

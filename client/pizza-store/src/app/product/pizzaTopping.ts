export class PizzaTopping {

  id:String;
  price:String;
  name: String;

  constructor(data:any) {
    this.id = data.id;
    this.price = data.price;
    this.name = data.name;
  }
}

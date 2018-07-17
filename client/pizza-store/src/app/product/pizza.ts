export class Pizza {

  id:String;
  size: String;
  dough: String;
  price:number;
  productStatus;

  constructor(data:any) {
    this.id = data.id;
    this.size = data.size;
    this.size = this.size.toLowerCase();
    this.dough = data.dough;
    this.dough = this.dough.toLowerCase();
    this.price = data.price;
    this.productStatus = data.productStatus;
  }
}

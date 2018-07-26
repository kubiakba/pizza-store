export class NewOrder {

  id: String;
  userEmail: String;
  productsId: String[];
  constructor(){
    this.productsId = [];
  }
}

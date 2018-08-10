export class OrderProductsDTO {
  orderId: String;
  productsIds: String[];

  constructor(orderId: String, products: String[]) {
    this.orderId = orderId;
    this.productsIds = products;
  }
}

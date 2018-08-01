import {Component, Input} from '@angular/core';
import {OrderService} from "../order/order.service";
import {NewOrder} from "../order/newOrder";

@Component({
  selector: 'app-add-user-view',
  template: `
    <form>
      <div class="form-group row">
        <label for="name" class="col-sm-1 col-form-label">Name</label>
        <div class="col-xm-4">
          <input type="text" [(ngModel)]="user.name" name="name" class="form-control" id="Name">
        </div>
      </div>
      <div class="form-group row">
        <label for="surname" class="col-sm-1 col-form-label">Surname</label>
        <div class="col-xm-4">
          <input type="text" [(ngModel)]="user.surname" name="surname"  class="form-control" id="surname">
        </div>
      </div>
      <div class="form-group row">
        <label for="telephone" class="col-sm-1 col-form-label">Telephone</label>
        <div class="col-xm-4">
          <input type="text" [(ngModel)]="user.telephone" name="telephone" class="form-control" id="telephone">
        </div>
      </div>
      <div class="form-group row">
        <label for="city" class="col-sm-1 col-form-label">City</label>
        <div class="col-xm-4">
          <input type="text" [(ngModel)]="user.city" name="city" class="form-control" id="city">
        </div>
      </div>
      <div class="form-group row">
        <label for="street" class="col-sm-1 col-form-label">Street</label>
        <div class="col-xm-4">
          <input type="text" [(ngModel)]="user.street" name="street" class="form-control" id="street">
        </div>
      </div>
      <div class="form-group row">
        <label for="streetNumber" class="col-sm-1 col-form-label">Number</label>
        <div class="col-xm-4">
          <input type="text" [(ngModel)]="user.streetNumber" name="streetNumber" class="form-control" id="streetNumber">
        </div>
      </div>
      <div class="form-group row">
        <label for="zipCode" class="col-sm-1 col-form-label">Zip Code</label>
        <div class="col-xm-4">
          <input type="text" [(ngModel)]="user.zipCode" name="zipCode" class="form-control" id="zipCode">
        </div>
      </div>
    </form>
    <button type="button" (click)="createOrder()" class="btn btn-primary">Confirm</button>
  `
})
export class AddUserViewComponent {

  orderId: String;
  @Input() order: NewOrder;
  user: any = {};

  constructor(private orderService: OrderService) {
  }

  createOrder() {
    this.orderService.startOrder().subscribe(value => {
      this.orderId = value.id;

      this.order.productsId.forEach(productId => {
        this.orderService.addProductToOrder(this.orderId, productId).subscribe();
      });
    });
  }
}

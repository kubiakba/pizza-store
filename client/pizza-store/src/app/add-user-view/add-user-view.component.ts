import {Component, Input} from '@angular/core';
import {OrderService} from "../order/order.service";
import {NewOrder} from "../order/newOrder";
import {UserService} from "../user/user.service";
import {User} from "../user/user";

@Component({
  selector: 'app-add-user-view',
  template: `
    <div *ngIf="!orderFinished">
      <form>
        <div class="form-group row">
          <label for="name" class="col-sm-1 col-form-label">Email</label>
          <div class="col-xm-4">
            <input type="text" [(ngModel)]="user.email" name="email" class="form-control" id="email">
          </div>
        </div>
        <div class="form-group row">
          <label for="name" class="col-sm-1 col-form-label">Name</label>
          <div class="col-xm-4">
            <input type="text" [(ngModel)]="user.name" name="name" class="form-control" id="Name">
          </div>
        </div>
        <div class="form-group row">
          <label for="surname" class="col-sm-1 col-form-label">Surname</label>
          <div class="col-xm-4">
            <input type="text" [(ngModel)]="user.surname" name="surname" class="form-control" id="surname">
          </div>
        </div>
        <div class="form-group row">
          <label for="telephone" class="col-sm-1 col-form-label">Telephone</label>
          <div class="col-xm-4">
            <input type="text" [(ngModel)]="telephone.number" name="telephone" class="form-control" id="telephone">
          </div>
        </div>
        <div class="form-group row">
          <label for="city" class="col-sm-1 col-form-label">City</label>
          <div class="col-xm-4">
            <input type="text" [(ngModel)]="address.city" name="city" class="form-control" id="city">
          </div>
        </div>
        <div class="form-group row">
          <label for="street" class="col-sm-1 col-form-label">Street</label>
          <div class="col-xm-4">
            <input type="text" [(ngModel)]="address.street" name="street" class="form-control" id="street">
          </div>
        </div>
        <div class="form-group row">
          <label for="streetNumber" class="col-sm-1 col-form-label">Number</label>
          <div class="col-xm-4">
            <input type="text" [(ngModel)]="address.streetNumber" name="streetNumber" class="form-control" id="streetNumber">
          </div>
        </div>
        <div class="form-group row">
          <label for="zipCode" class="col-sm-1 col-form-label">Zip Code</label>
          <div class="col-xm-4">
            <input type="text" [(ngModel)]="address.postCode" name="postCode" class="form-control" id="zipCode">
          </div>
        </div>
      </form>
      <button type="button" (click)="createOrder()" class="btn btn-primary">Confirm</button>
    </div>
    <div *ngIf="orderFinished">
      <app-order-confirmation-view></app-order-confirmation-view>
    </div>
  `
})
export class AddUserViewComponent {

  orderId: String;
  @Input() order: NewOrder;
  user: any = {};
  address: any = {};
  telephone: any = {};
  createdUser: User;
  orderFinished = false;

  constructor(private orderService: OrderService, private userService: UserService) {
  }

  createOrder() {
    this.addFieldsToUser();
    this.createUser();
    this.addProductsToOrder();
    this.orderFinished = true;
  }

  private addFieldsToUser() {
    this.user.address = this.address;
    this.user.telephone = this.telephone;
  }

  private createUser() {
    this.userService.createNotRegisteredUser(new User(this.user)).subscribe(user => {
      this.createdUser = new User(user);
    });
  }

  private addProductsToOrder() {
    this.orderService.startOrder().subscribe(order => {
      this.orderId = order.id;
      this.order.productsId.forEach(productId => {
        this.orderService.addProductToOrder(this.orderId, productId).subscribe();
      });
    });
  }
}

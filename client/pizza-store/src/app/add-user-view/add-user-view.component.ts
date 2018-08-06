import {Component, Input} from '@angular/core';
import {OrderService} from "../order/order.service";
import {UserService} from "../user/user.service";
import {NewOrderDTO} from "../order/newOrderDTO";

@Component({
  selector: 'app-add-user-view',
  template: `
    <div *ngIf="!orderFinished">
      <form>
        <div class="form-group row">
          <label for="name" class="col-sm-1 col-form-label">Email</label>
          <div class="col-xm-4">
            <input type="text" [(ngModel)]="data.email" name="email" class="form-control" id="email">
          </div>
        </div>
        <div class="form-group row">
          <label for="name" class="col-sm-1 col-form-label">Name</label>
          <div class="col-xm-4">
            <input type="text" [(ngModel)]="deliveryInfo.name" name="name" class="form-control" id="Name">
          </div>
        </div>
        <div class="form-group row">
          <label for="surname" class="col-sm-1 col-form-label">Surname</label>
          <div class="col-xm-4">
            <input type="text" [(ngModel)]="deliveryInfo.surname" name="surname" class="form-control" id="surname">
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
      <div *ngIf="errorMessage.size > 0">
        <div *ngFor="let message of errorMessage">
          {{message}}
        </div>
      </div>
      <button [disabled]="!allInputsAreFilled()" type="button" (click)="createOrder()" class="btn btn-primary">Confirm</button>
    </div>
    <div *ngIf="orderFinished">
      <app-order-confirmation-view [orderId]="orderId" ></app-order-confirmation-view>
    </div>
  `
})
export class AddUserViewComponent {

  orderId: String;
  @Input() productIds: String[];
  data: any = {};
  deliveryInfo :any = {};
  address: any = {};
  telephone: any = {};
  createdOrder: NewOrderDTO;
  orderFinished = false;
  errorMessage: Set<String> = new Set<String>();

  constructor(private orderService: OrderService, private userService: UserService) {
  }

  createOrder() {
    this.addFieldsToUser();
    this.addProductsToOrder();
  }

  private addFieldsToUser() {
    this.deliveryInfo.address = this.address;
    this.deliveryInfo.telephone = this.telephone;
    this.data.deliveryInfo = this.deliveryInfo;
    this.createdOrder = new NewOrderDTO(this.data);
  }

  private addProductsToOrder() {
    this.orderService.startOrder(this.createdOrder).subscribe(order => {
      this.orderId = order.id;
      this.productIds.forEach(productId => {
        this.orderService.addProductToOrder(this.orderId, productId).subscribe(next => {
        }, error => this.errorMessage.add(error.error.errorCode));
      });
    }, error => this.errorMessage.add(error.error.errorCode),() => {
      this.orderFinished = true;
    });
  }

  allInputsAreFilled() {
    if (this.data.email != null && this.deliveryInfo.name != null
      && this.deliveryInfo.surname != null && this.telephone.number != null
      && this.address.street != null && this.address.streetNumber != null
      && this.address.city != null && this.address.postCode != null) {
      return true;
    }
  }
}

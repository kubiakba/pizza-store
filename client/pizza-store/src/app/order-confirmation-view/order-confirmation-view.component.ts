import {Component, Input, OnInit} from '@angular/core';
import {OrderService} from "../order/order.service";

@Component({
  selector: 'app-order-confirmation-view',
  template: `
    <div style="background-color: #117a8b">
      <div *ngIf="orderStarted && errorMessage.length == 0">
        <div style="font-size: 24px" align="center">
          <p>Food is going to your home :)</p>
        </div>
      </div>
      <div *ngIf="!orderStarted && errorMessage.length > 0">
        <div style="font-size: 24px" align="center">
          <p>Error occured: {{errorMessage}} </p>
        </div>
      </div>
    </div>  `
})
export class OrderConfirmationViewComponent implements OnInit {

  @Input() orderId: String;
  orderStarted = false;
  errorMessage: String = "";

  constructor(private orderService: OrderService) {
  }

  ngOnInit(): void {
    this.orderService.setOrderToRealization(this.orderId).subscribe(order => {
      this.orderStarted = true;
    }, error => this.errorMessage = error);
  }


}

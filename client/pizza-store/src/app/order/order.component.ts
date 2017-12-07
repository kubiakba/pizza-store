import { Component, OnInit } from '@angular/core';
import {OrderService} from "../order-service/order.service";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  order: any;
  metadata :String;

  constructor(private orderService: OrderService) { }

  onKey(event){
    this.orderService.getOrder(event.target.value).subscribe(data =>{
      this.order = data;
    });
  }

  ngOnInit() {
  }
}

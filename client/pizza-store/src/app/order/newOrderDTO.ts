import {DeliveryInfoDTO} from "./deliveryInfoDTO";

export class NewOrderDTO {

  email: String;
  deliveryInfoDTO: DeliveryInfoDTO;

  constructor(data:any) {
    this.email = data.email;
    this.deliveryInfoDTO = new DeliveryInfoDTO(data.deliveryInfo);
  }
}

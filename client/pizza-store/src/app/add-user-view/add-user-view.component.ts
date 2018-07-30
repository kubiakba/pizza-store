import {Component} from '@angular/core';

@Component({
  selector: 'app-add-user-view',
  template: `
    <form>
      <div class="form-group row">
        <label for="name" class="col-sm-1 col-form-label">Name</label>
        <div class="col-xm-4">
          <input type="text" class="form-control" id="Name">
        </div>
      </div>
      <div class="form-group row">
        <label for="surname" class="col-sm-1 col-form-label">Surname</label>
        <div class="col-xm-4">
          <input type="text" class="form-control" id="surname">
        </div>
      </div>
      <div class="form-group row">
        <label for="telephone" class="col-sm-1 col-form-label">Telephone</label>
        <div class="col-xm-4">
          <input type="text" class="form-control" id="telephone">
        </div>
      </div>
      <div class="form-group row">
        <label for="city" class="col-sm-1 col-form-label">City</label>
        <div class="col-xm-4">
          <input type="text" class="form-control" id="city">
        </div>
      </div>
      <div class="form-group row">
        <label for="street" class="col-sm-1 col-form-label">Street</label>
        <div class="col-xm-4">
          <input type="text" class="form-control" id="street">
        </div>
      </div>
      <div class="form-group row">
        <label for="streetNumber" class="col-sm-1 col-form-label">Number</label>
        <div class="col-xm-4">
          <input type="text" class="form-control" id="streetNumber">
        </div>
      </div>
      <div class="form-group row">
        <label for="zipCode" class="col-sm-1 col-form-label">Zip Code</label>
        <div class="col-xm-4">
          <input type="text" class="form-control" id="zipCode">
        </div>
      </div>
    </form>
    <button type="button" class="btn btn-primary">Confirm</button>
  `
})
export class AddUserViewComponent {

  constructor() {
  }
}

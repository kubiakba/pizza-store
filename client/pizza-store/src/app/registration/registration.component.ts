import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Address} from "../user-service/address";
import {Telephone} from "../user-service/telephone";
import {User} from "../user-service/user";
import {UserService} from "../user-service/user.service";
import {resetFakeAsyncZone} from "@angular/core/testing";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private userService :UserService) { }

  myForm: FormGroup;

  ngOnInit() {
    this.myForm = new FormGroup({
      email: new FormControl(''),
      password: new FormControl(''),
      name: new FormControl(''),
      surname: new FormControl(''),
      telephone: new FormControl(''),
      city: new FormControl(''),
      postCode: new FormControl(''),
      street: new FormControl(''),
      streetNumber: new FormControl('')
    });
  }

  onSubmit(form: FormGroup){
    let user = this.createUserFromForm(form);
    this.userService.createUser(user).subscribe(res=> console.log(res));
  }

  createUserFromForm(form :FormGroup) :User{
    let user = new User();
    user.email = form.value.email;
    user.name = form.value.name;
    user.surname = form.value.surname;
    user.password = form.value.password;

    let address = new Address();
    address.city = form.value.city;
    address.postCode = form.value.postCode;
    address.street = form.value.street;
    address.streetNumber = form.value.streetNumber;
    user.address = address;

    let telephone = new Telephone();
    telephone.number = form.value.telephone;
    user.telephone = telephone;

    return user;
  }
}

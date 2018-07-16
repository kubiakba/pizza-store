import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  template: `<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" routerLink="/view-users">user</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" routerLink="/view-products">products</a>
      </li>
    </ul>
  </div>
</nav>`
})
export class NavbarComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}

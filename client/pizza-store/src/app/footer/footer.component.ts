import {Component} from '@angular/core';

@Component({
  selector: 'app-footer',
  template: `<footer class="footer">
    <div class="container">
      <span  style="float: right" class="text-muted">bkubiak&copy;2018 All Rights Reserved</span>
    </div>
  </footer>
  `,
  styleUrls: ['./footer.css']
})
export class FooterComponent {

  constructor() {
  }
}

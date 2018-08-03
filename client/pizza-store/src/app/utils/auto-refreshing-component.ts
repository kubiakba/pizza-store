import {OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {NavigationEnd, Router} from '@angular/router';
import {filter} from 'rxjs/operators';
import {AppInjector} from "./injector";

export abstract class AutoRefreshingComponent implements OnInit {

  public routerEventsSubscription: Subscription;
  protected router: Router;

  constructor() {
    this.router = AppInjector.get(Router);
  }

  /**
   * Initialization behavior. Note that derived classes must not implement OnInit.
   * Use initialize() on derived classes instead.
   */
  ngOnInit() {
    this.initialize();
    this.routerEventsSubscription = this.router.events.pipe(filter(x => x instanceof NavigationEnd)).subscribe(res => {
      this.initialize();
    });
  }

  /**
   * Function that allows derived components to define an initialization behavior
   */
  abstract initialize(): void;
}

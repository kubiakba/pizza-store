# Overview
The goal of project is to create pizza store where you can order
 different kind of foods.

## Done
- [x] User can add information like city, address, telephone
- [x] User can order products like pizza, toppings, kebab, salads, cola.
- [] User can use discounts.
- [] User can collect points.

## Scenarios
* when i go to /products then i see all products
* when i go to /order/{userEmail} i start making order
* when i go to /order/{orderId}/{productId} i add product to order
* when i go to /discount then i see available discounts
* when i go to /user/bonus then i see how much points i gather

## Modules
* product
* order
* user
* discount

## Dependencies
* user choose product to buy
* order asks for product info
* order asks for user info
* order calculate discounts
* order give points to user
* user checks bonus points
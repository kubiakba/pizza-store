#!/bin/bash

#create products
product_id=$(curl --silent -d '{"type":"Kebab", "price":10.0, "description":"desc1","kebabName":"nazwa1"}' -H "Content-Type: application/json" -X POST http://localhost:8081/products | awk -F ":||," '{print $4}' | awk '{print substr($0, 2, length($0) - 2)}')
curl -d '{"type":"PizzaTopping}", "price":6.0, "Name":"salami"}' -H "Content-Type: application/json" -X POST http://localhost:8081/products
curl -d '{"type":"Pizza", "price":8.0, "size":"SMALL","dough":"THIN"}' -H "Content-Type: application/json" -X POST http://localhost:8081/products

#create users
curl -d '{"email":"1232@gmail.com","password":"pass","name":"name","surname":"surname","address":{"city":"c","street":"st","streetNumber":"sn","postCode":"p"},"telephone":{"number":"234"},"role":"USER"}' -H "Content-Type: application/json" -X POST http://localhost:8081/users
curl -d '{"email":"1@gmail.com","password":"pass","name":"name","surname":"surname","address":{"city":"c","street":"st","streetNumber":"sn","postCode":"p"},"telephone":{"number":"234"},"role":"USER"}' -H "Content-Type: application/json" -X POST http://localhost:8081/users

#create orders
order_id=$(curl --silent -d '{"email":"1@gmail.com"}' -H "Content-Type: application/json" -X POST http://localhost:8081/orders | awk -F ":||," '{print $2}' | awk '{print substr($0, 2, length($0) - 2)}')

#add product to order
curl -H "Content-Type: application/json" -X PUT http://localhost:8081/orders/$order_id/$product_id

#!/bin/bash

while getopts h:u:p: option
do
 case "${option}"
 in
 h) host=${OPTARG};;
 u) user=${OPTARG};;
 p) password=${OPTARG};;
 esac
done

#create products
product_id=$(curl --silent -u $user:$password -d '{"type":"Kebab", "price":10.0, "description":"Small","kebabName":"Fet Kebab"}' -H "Content-Type: application/json" -X POST $host/admin/products | awk -F ":||," '{print $4}' | awk '{print substr($0, 2, length($0) - 2)}')
curl -u $user:$password -d '{"type":"PizzaTopping", "price":6.0, "toppingName":"salami"}' -H "Content-Type: application/json" -X POST $host/admin/products
curl -u $user:$password -d '{"type":"Pizza", "price":8.0, "size":"SMALL","dough":"THIN"}' -H "Content-Type: application/json" -X POST $host/admin/products

#create users
curl -d '{"email":"1232@gmail.com","password":"pass","name":"Adam","surname":"Kościelniak","address":{"city":"Poznań","street":"kośćielna","streetNumber":"22","postCode":"31-283"},"telephone":{"number":"234"},"role":"USER"}' -H "Content-Type: application/json" -X POST $host/users
curl -d '{"email":"1@gmail.com","password":"pass","name":"Witold","surname":"Król","address":{"city":"Warszawa","street":"Niepodległości","streetNumber":"34","postCode":"43-344"},"telephone":{"number":"234"},"role":"USER"}' -H "Content-Type: application/json" -X POST $host/users

#create orders
order_id=$(curl --silent -d '{"email":"1@gmail.com"}' -H "Content-Type: application/json" -X POST $host/orders | awk -F ":||," '{print $2}' | awk '{print substr($0, 2, length($0) - 2)}')

#add product to order
curl -H "Content-Type: application/json" -X PUT $host/orders/$order_id/$product_id

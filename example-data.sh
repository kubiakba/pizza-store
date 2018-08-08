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

token=$(curl -v -d '{"username":"'"$user"'","password":"'"$password"'"}' -H "Content-Type: application/json" -X POST $host/auth | awk -F ":||," '{print$4}' | awk '{print substr($0, 2, length($0) - 3)}')

#create products
product_id=$(curl -v -d '{"type":"Kebab", "price":10.0, "description":"Small","kebabName":"Fet Kebab"}' -H 'Content-Type: application/json' -H "Authorization: Bearer $token" -X POST $host/admin/products | awk -F ":||," '{print $4}' | awk '{print substr($0, 2, length($0) - 2)}')
curl -v -d '{"type":"PizzaTopping", "price":6.0, "toppingName":"salami"}' -H "Content-Type: application/json" -H 'Content-Type: application/json' -H "Authorization: Bearer $token" -X POST $host/admin/products
curl -v -d '{"type":"Pizza", "price":8.0, "size":"SMALL","dough":"THIN"}' -H "Content-Type: application/json" -H 'Content-Type: application/json' -H "Authorization: Bearer $token" -X POST $host/admin/products

#create users
curl -d '{"email":"1232@gmail.com","password":"pass"}' -H "Content-Type: application/json" -X POST $host/users
curl -d '{"email":"1@gmail.com","password":"pass"}' -H "Content-Type: application/json" -X POST $host/users

#create orders
order_id=$(curl -v -d '{"email":"1@gmail.com","deliveryInfoDTO":{"name":"Adam","surname":"Kościelniak","addressDTO":{"city":"Poznań","street":"kośćielna","streetNumber":"22","postCode":"31-283"},"telephoneDTO":{"number":"234"}}}' -H "Content-Type: application/json" -X POST $host/orders | awk -F ":||," '{print $2}' | awk '{print substr($0, 2, length($0) - 2)}')

#add product to order
curl -v -H "Content-Type: application/json" -X PUT $host/orders/$order_id/$product_id

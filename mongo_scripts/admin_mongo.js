/*password = admin*/

db.user.insert({
    "_id": "admin@admin.pl",
    "password": "{bcrypt}$2a$10$GW1WQ.VAg.hhov/GnBl3Z.qb7ddkHro2jEJtaO1ozcHbQw6rO6S8G",
    "role": "ADMIN",
    "status": "ACTIVE",
    "_class": "pl.bk.pizza.store.domain.customer.user.User"
});

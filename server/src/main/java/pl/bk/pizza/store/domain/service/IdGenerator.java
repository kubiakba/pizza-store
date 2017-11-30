package pl.bk.pizza.store.domain.service;

import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Service
public class IdGenerator {

    public String generateID(){
        return randomUUID().toString();
    }
}

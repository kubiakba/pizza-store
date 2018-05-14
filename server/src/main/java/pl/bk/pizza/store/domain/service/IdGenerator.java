package pl.bk.pizza.store.domain.service;

import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Component
public class IdGenerator
{
    private IdGenerator()
    {
    }
    
    public static String generateID()
    {
        return randomUUID().toString();
    }
}

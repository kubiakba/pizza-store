package pl.bk.pizza.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"pl.bk.search", "pl.bk.common", "pl.bk.pizza"})
public class Initializer
{
    public static void main(String[] args)
    {
        SpringApplication.run(Initializer.class, args);
    }
}


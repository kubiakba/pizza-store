package pl.bk.pizza.store.domain.product;

import org.springframework.stereotype.Service;

import pl.bk.pizza.store.domain.service.IdGenerator;

import java.math.BigDecimal;

@Service
public class ProductFactory {

    private final IdGenerator idGenerator;

    public ProductFactory(IdGenerator idGenerator){
        this.idGenerator = idGenerator;
    }

    public Product createProduct(BigDecimal price, ProductInfo productInfo) {
        return new Product(idGenerator.generateID(), price, productInfo);
    }

}

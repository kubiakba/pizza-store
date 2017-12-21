package pl.bk.pizza.store.domain.product;

import java.util.List;

public interface ProductRepository {

    void save(Product product);

    Product getProductById(String id);

    List<Product> getAllProducts();

    List<Product> getAllPizzas();

    List<Product> getAllKebabs();

    List<Product> getAllPizzaToppings();
}

package pl.bk.pizza.store.infrastructure.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import pl.bk.pizza.store.domain.product.Kebab;
import pl.bk.pizza.store.domain.product.Pizza;
import pl.bk.pizza.store.domain.product.PizzaTopping;
import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.ProductRepository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class ProductDAO implements ProductRepository {

    private static final String COLLECTION_NAME = "product";

    private final MongoTemplate mongoTemplate;

    public ProductDAO(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(Product product) {
        mongoTemplate.save(product, COLLECTION_NAME);
    }

    @Override
    public Product getProductById(String id) {
        return mongoTemplate.findById(id, Product.class, COLLECTION_NAME);
    }

    @Override
    public List<Product> getAllProducts() {
        return mongoTemplate.findAll(Product.class, COLLECTION_NAME);
    }

    @Override
    public List<Product> getAllPizzas() {
        return mongoTemplate.find(query(where("productInfo._class").is(Pizza.class.getCanonicalName())),
            Product.class, COLLECTION_NAME);
    }

    @Override
    public List<Product> getAllKebabs() {
        Query query = new Query().addCriteria(Criteria.where("productInfo._class").is(Kebab.class.getCanonicalName()));
        return mongoTemplate.find(query, Product.class, COLLECTION_NAME);
    }

    @Override
    public List<Product> getAllPizzaToppings() {
        Query query = new Query().addCriteria(Criteria.where("productInfo._class").is(
            PizzaTopping.class.getCanonicalName()));
        return mongoTemplate.find(query, Product.class, COLLECTION_NAME);
    }


}

package pl.bk.pizza.store.infrastructure.dao.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.ProductRepository;

import java.util.List;

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


}

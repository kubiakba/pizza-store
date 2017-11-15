package pl.bk.pizza.store.infrastructure.dao.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import pl.bk.pizza.store.domain.user.User;
import pl.bk.pizza.store.domain.user.UserRepository;

@Repository
public class UserDAO implements UserRepository{

    private static final String COLLECTION_NAME = "user";

    private final MongoTemplate mongoTemplate;

    public UserDAO(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    public void save(User user){
        mongoTemplate.save(user, COLLECTION_NAME);
    }

    public User findById(String id){
        return mongoTemplate.findById(id, User.class);
    }

}

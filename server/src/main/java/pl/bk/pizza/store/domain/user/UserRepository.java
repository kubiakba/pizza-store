package pl.bk.pizza.store.domain.user;

public interface UserRepository {

    void save(User user);

    User findById(String id);
}

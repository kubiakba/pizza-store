package pl.bk.pizza.store.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bk.pizza.store.application.dto.user.NewUserDTO;
import pl.bk.pizza.store.application.dto.user.UserDTO;
import pl.bk.pizza.store.application.mapper.customer.NewUserMapper;
import pl.bk.pizza.store.application.mapper.customer.UserMapper;
import pl.bk.pizza.store.domain.customer.user.Points;
import pl.bk.pizza.store.domain.customer.user.User;
import pl.bk.pizza.store.domain.customer.user.UserRepository;
import pl.bk.pizza.store.domain.exception.DuplicateEntityException;
import pl.bk.pizza.store.domain.exception.ErrorCode;
import pl.bk.pizza.store.domain.exception.MissingEntityException;
import reactor.core.publisher.Mono;

import static pl.bk.pizza.store.domain.exception.ErrorCode.MISSING_USER;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

@Service
@AllArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final NewUserMapper newUserMapper;
    private final UserMapper userMapper;
    
    public Mono<UserDTO> createUser(NewUserDTO userDTO)
    {
        return Mono
            .just(userDTO)
            .doOnNext((newUser) -> Mono.just(newUser)
                                    .flatMap(it -> userRepository.findById(it.getEmail()))
                                    .doOnNext(this::userShouldNotExists))
            .map(newUserMapper::mapFromDTO)
            .doOnNext(userRepository::save)
            .map(userMapper::mapToDTO);
    }
    
    public Mono<Points> getBonusPoints(String email)
    {
        return userRepository
            .findById(email)
            .doOnNext(this::userShouldExists)
            .map(User::getPoints);
    }
    
    public Mono<UserDTO> getUser(String email)
    {
        return userRepository
            .findById(email)
            .doOnNext(this::userShouldExists)
            .map(userMapper::mapToDTO);
    }
    
    public void deactivateUser(String email)
    {
        userRepository
            .findById(email)
            .doOnNext(this::userShouldExists)
            .doOnNext(User::deactivateUser)
            .subscribe(userRepository::save);
    }
    
    public void addPointsToUser(String email, Integer points)
    {
        userRepository
            .findById(email)
            .doOnNext(this::userShouldExists)
            .doOnNext(user -> user.addPoints(points))
            .doOnNext(userRepository::save);
    }
    
    private void userShouldNotExists(User user)
    {
        check(
            user != null, () -> new DuplicateEntityException(
                "User with email: " + user.getEmail() + " already exists.",
                ErrorCode.USER_EXISTS
            ));
    }
    
    private void userShouldExists(User user)
    {
        check(user == null, () -> new MissingEntityException(
            "user with email: " + user.getEmail() + "does not exists",
            MISSING_USER
        ));
    }
}

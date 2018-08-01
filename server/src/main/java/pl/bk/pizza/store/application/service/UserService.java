package pl.bk.pizza.store.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bk.pizza.store.application.dto.user.NewNotRegisteredUserDTO;
import pl.bk.pizza.store.application.dto.user.NewUserDTO;
import pl.bk.pizza.store.application.dto.user.UserDTO;
import pl.bk.pizza.store.application.mapper.customer.NewNotRegisteredUserMapper;
import pl.bk.pizza.store.application.mapper.customer.NewUserMapper;
import pl.bk.pizza.store.application.mapper.customer.UserMapper;
import pl.bk.pizza.store.domain.customer.user.User;
import pl.bk.pizza.store.domain.customer.user.UserRepository;
import reactor.core.publisher.Mono;

import static pl.bk.pizza.store.domain.validator.customer.UserValidator.userShouldExists;
import static pl.bk.pizza.store.domain.validator.customer.UserValidator.userShouldNotExists;
import static reactor.core.publisher.Mono.just;

@Service
@AllArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final NewUserMapper newUserMapper;
    private final UserMapper userMapper;
    private final NewNotRegisteredUserMapper notRegisteredUserMapper;
    
    public Mono<UserDTO> createUser(NewUserDTO userDTO)
    {
        return just(userDTO)
            .flatMap(it -> userRepository.findById(it.getEmail()))
            .flatMap(it -> userShouldNotExists(userDTO.getEmail()))
            .then(just(userDTO))
            .map(newUserMapper::mapFromDTO)
            .flatMap(userRepository::save)
            .map(userMapper::mapToDTO);
    }
    
    public Mono<UserDTO> createNotRegisteredUser(NewNotRegisteredUserDTO userDTO)
    {
        return just(userDTO)
            .map(notRegisteredUserMapper::mapFromDTO)
            .flatMap(userRepository::save)
            .map(userMapper::mapToDTO);
    }
    
    public Mono<Integer> getBonusPoints(String email)
    {
        return userRepository
            .findById(email)
            .switchIfEmpty(userShouldExists(email))
            .map(User::getPoints);
    }
    
    public Mono<UserDTO> getUser(String email)
    {
        return userRepository
            .findById(email)
            .switchIfEmpty(userShouldExists(email))
            .map(userMapper::mapToDTO);
    }
    
    public Mono<UserDTO> deactivateUser(String email)
    {
        return userRepository
            .findById(email)
            .switchIfEmpty(userShouldExists(email))
            .map(User::deactivateUser)
            .flatMap(userRepository::save)
            .map(userMapper::mapToDTO);
    }
    
    Mono<User> addPoints(String email, Integer points)
    {
        return userRepository
            .findById(email)
            .switchIfEmpty(userShouldExists(email))
            .map(user -> user.addPoints(points))
            .flatMap(userRepository::save);
    }
}

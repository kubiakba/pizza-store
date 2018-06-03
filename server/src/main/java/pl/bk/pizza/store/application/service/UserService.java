package pl.bk.pizza.store.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bk.pizza.store.application.dto.user.NewUserDTO;
import pl.bk.pizza.store.application.dto.user.UserDTO;
import pl.bk.pizza.store.application.mapper.customer.NewUserMapper;
import pl.bk.pizza.store.application.mapper.customer.UserMapper;
import pl.bk.pizza.store.domain.customer.user.User;
import pl.bk.pizza.store.domain.customer.user.UserRepository;
import pl.bk.pizza.store.domain.validator.customer.UserValidator;
import reactor.core.publisher.Mono;

import static pl.bk.pizza.store.domain.validator.customer.UserValidator.userShouldExists;

@Service
@AllArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final NewUserMapper newUserMapper;
    private final UserMapper userMapper;
    
    public Mono<UserDTO> createUser(NewUserDTO userDTO)
    {
        final Mono<NewUserDTO> userDto = Mono.just(userDTO);
        
        final Mono<Error> duplicateUser = userDto
            .flatMap(it -> userRepository.findById(it.getEmail()))
            .flatMap(it -> new UserValidator().userShouldNotExists(userDTO.getEmail()));
        
        return duplicateUser
            .then(userDto)
            .map(newUserMapper::mapFromDTO)
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

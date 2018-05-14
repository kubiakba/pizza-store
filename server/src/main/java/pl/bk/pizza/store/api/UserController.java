package pl.bk.pizza.store.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.bk.pizza.store.application.dto.user.NewUserDTO;
import pl.bk.pizza.store.application.dto.user.UserDTO;
import pl.bk.pizza.store.application.service.UserService;
import pl.bk.pizza.store.domain.customer.user.Points;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
class UserController
{
    private final UserService userService;
    
    @ResponseStatus(CREATED)
    @PostMapping
    public Mono<ResponseEntity<UserDTO>> addUser(@RequestBody NewUserDTO userDTO)
    {
        final Mono<UserDTO> user = userService.createUser(userDTO);
        final Mono<URI> uri = user.map(it -> URI.create("/users/" + it.getEmail()));
        
        return uri
            .zipWith(user)
            .map(it -> ResponseEntity
                .created(it.getT1())
                .body(it.getT2()));
    }
    
    @ResponseStatus(OK)
    @GetMapping("/{email:.+}")
    public Mono<UserDTO> getUser(@PathVariable String email)
    {
        return userService.getUser(email);
    }
    
    @ResponseStatus(OK)
    @GetMapping("/{email}/bonus")
    public Mono<Points> getBonusPoints(@PathVariable String email)
    {
        return userService.getBonusPoints(email);
    }
    
    @ResponseStatus(NO_CONTENT)
    @PatchMapping("/{email}/deactivate")
    public void deactivate(@PathVariable String email)
    {
        userService.deactivateUser(email);
    }
}

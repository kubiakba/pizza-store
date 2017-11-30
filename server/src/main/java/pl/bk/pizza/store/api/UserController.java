package pl.bk.pizza.store.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.bk.pizza.store.application.dto.user.NewUserDTO;
import pl.bk.pizza.store.application.dto.user.UserDTO;
import pl.bk.pizza.store.application.service.UserService;

import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody NewUserDTO userDTO){
        UserDTO user = userService.createUser(userDTO);
        URI uri = URI.create("/users/" + user.getEmail());
        return ResponseEntity.created(uri).body(user);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{email:.+}")
    public void updateUser(@PathVariable String email, @RequestBody UserDTO userDTO){
        userService.updateUser(email, userDTO);
    }

    @ResponseStatus(OK)
    @GetMapping("/{email:.+}")
    public UserDTO getUser(@PathVariable String email){
         return userService.getUser(email);
    }

    @ResponseStatus(OK)
    @GetMapping("/{email}/bonus")
    public Integer getBonusPoints(@PathVariable String email){
        return userService.getBonusPoints(email);
    }

    @ResponseStatus(OK)
    @PutMapping("/{email}/{password}/authenticate")
    public Boolean authenticate(@PathVariable String email, @PathVariable String password){
        return userService.authenticate(email,password);
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping("/{email}/deactivate")
    public void deactivate(@PathVariable String email){
        userService.deactivateUser(email);
    }

}

package pl.bk.pizza.store.application.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.bk.pizza.store.application.dto.user.NewUserDTO;
import pl.bk.pizza.store.application.dto.user.UserDTO;
import pl.bk.pizza.store.application.utils.DtoMapper;
import pl.bk.pizza.store.domain.exception.MissingEntityException;
import pl.bk.pizza.store.domain.user.User;
import pl.bk.pizza.store.domain.user.UserRepository;

import static pl.bk.pizza.store.domain.exception.ErrorCode.*;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;

    public UserService(UserRepository userRepository,DtoMapper dtoMapper){
        this.dtoMapper = dtoMapper;
        this.userRepository = userRepository;
    }

    public UserDTO createUser(NewUserDTO userDTO) {
        User user = dtoMapper.mapTo((userDTO));
        userRepository.save(user);
        return dtoMapper.mapTo(user);
    }

    public void updateUser(String email, UserDTO userDTO) {
        final User user = userRepository.findById(email);
        check(user == null, () -> new MissingEntityException("user with email: " + email + "does not exists",
            MISSING_USER));
        if(userDTO.getName() != null) user.changeName(userDTO.getName());
        if(userDTO.getSurname() != null) user.changeSurname(userDTO.getSurname());
        if(userDTO.getAddress() != null) user.changeAddress(dtoMapper.mapTo(userDTO.getAddress()));
        if(userDTO.getTelephone() != null) user.changeTelephone(dtoMapper.mapTo(userDTO.getTelephone()));
        userRepository.save(user);
    }

    public int getBonusPoints(String email) {
        User user = userRepository.findById(email);
        check(user == null, () -> new MissingEntityException("user with email: " + email + "does not exists",
            MISSING_USER));
        return user.getPoints();
    }

    public Boolean authenticate(String email, String password){
        final User user = userRepository.findById(email);
        check(user == null, () -> new MissingEntityException("user with email: " + email + "does not exists",
            MISSING_USER));
        return new BCryptPasswordEncoder().matches(password, user.getPassword());
    }

    public UserDTO getUser(String email) {
        final User user = userRepository.findById(email);
        check(user == null, () -> new MissingEntityException("user with email: " + email + "does not exists",
            MISSING_USER));
        return dtoMapper.mapTo(user);
    }

    public void deactivateUser(String email) {
        final User user = userRepository.findById(email);
        check(user == null, () -> new MissingEntityException("user with email: " + email + "does not exists",
            MISSING_USER));
        user.deactivateUser();
        userRepository.save(user);
    }
}

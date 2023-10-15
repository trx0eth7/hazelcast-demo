package ru.trx.hazelcastdemo.integration.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.trx.hazelcastdemo.UserService;
import ru.trx.hazelcastdemo.integration.exception.UserNotFoundException;
import ru.trx.hazelcastdemo.integration.mapper.UserMapper;
import ru.trx.hazelcastdemo.model.dto.CreateUserDto;
import ru.trx.hazelcastdemo.model.dto.UserDto;
import ru.trx.hazelcastdemo.model.entity.User;
import ru.trx.hazelcastdemo.repository.UserRepository;

import java.util.List;

/**
 * @author Alexander Vasiliev
 */
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> all() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtos(users);
    }

    @GetMapping("/users/{login}")
    public UserDto one(@PathVariable String login) {
        User user = userService.getByLogin(login);

        if (user == null) {
            throw new UserNotFoundException("Not found by login: " + login);
        }

        return userMapper.toDto(user);
    }

    @PostMapping("/users")
    public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
        User user = userMapper.toEntity(createUserDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

}


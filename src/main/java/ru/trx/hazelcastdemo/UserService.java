package ru.trx.hazelcastdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.trx.hazelcastdemo.model.entity.User;
import ru.trx.hazelcastdemo.repository.UserRepository;

/**
 * @author Alexander Vasiliev
 */
@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Cacheable("users")
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}

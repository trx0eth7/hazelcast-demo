package ru.trx.hazelcastdemo.model.dto;

import lombok.Value;
import ru.trx.hazelcastdemo.model.entity.User;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    private static final long serialVersionUID = -4780619419198516445L;
    private final UUID id;
    private final String login;
    private final String firstName;
    private final String lastName;
    private final String email;
}
package ru.trx.hazelcastdemo.model.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ru.trx.hazelcastdemo.model.entity.User}
 */
@Value
public class CreateUserDto implements Serializable {
    private static final long serialVersionUID = -5714257841477620691L;
    private final String login;
    private final String firstName;
    private final String lastName;
    private final String email;
}
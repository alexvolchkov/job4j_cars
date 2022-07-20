package ru.job4j.hibernate.service;

import ru.job4j.hibernate.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> add(User user);

    Optional<User> findUserByNameAndPwd(String name, String password);
}

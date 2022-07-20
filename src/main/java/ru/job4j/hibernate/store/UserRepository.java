package ru.job4j.hibernate.store;

import ru.job4j.hibernate.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    Optional<User> add(User user);

    Optional<User> findUserByNameAndPwd(String name, String password);
}

package ru.job4j.hibernate.service;

import org.springframework.stereotype.Service;
import ru.job4j.hibernate.entity.User;
import ru.job4j.hibernate.store.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceDb implements UserService {
    private final UserRepository repository;

    public UserServiceDb(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> add(User user) {
       return repository.add(user);
    }

    @Override
    public Optional<User> findUserByNameAndPwd(String name, String password) {
        return repository.findUserByNameAndPwd(name, password);
    }
}

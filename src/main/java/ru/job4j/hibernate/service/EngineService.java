package ru.job4j.hibernate.service;

import ru.job4j.hibernate.entity.Engine;

import java.util.List;
import java.util.Optional;

public interface EngineService {
    List<Engine> findAll();

    Optional<Engine> findById(int id);

    void add(Engine engine);

    void update(int id, Engine engine);

}

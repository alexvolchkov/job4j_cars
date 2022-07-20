package ru.job4j.hibernate.store;

import ru.job4j.hibernate.entity.Engine;

import java.util.List;
import java.util.Optional;

public interface EngineRepository {
    List<Engine> findAll();

    Optional<Engine> findById(int id);

    void add(Engine engine);

    void update(int id, Engine engine);

}

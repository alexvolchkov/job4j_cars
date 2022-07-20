package ru.job4j.hibernate.service;

import org.springframework.stereotype.Service;
import ru.job4j.hibernate.entity.Engine;
import ru.job4j.hibernate.store.EngineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EngineServiceDb implements EngineService {
    private final EngineRepository repository;

    public EngineServiceDb(EngineRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Engine> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Engine> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void add(Engine engine) {
        repository.add(engine);
    }

    @Override
    public void update(int id, Engine engine) {
        repository.update(id, engine);
    }

}

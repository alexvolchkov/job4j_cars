package ru.job4j.hibernate.service;

import org.springframework.stereotype.Service;
import ru.job4j.hibernate.entity.Advertisement;
import ru.job4j.hibernate.store.AdRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdServiceDb implements AdService {
    private final AdRepository repository;

    public AdServiceDb(AdRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Advertisement> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Advertisement> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void add(Advertisement ad) {
        repository.add(ad);
    }

    @Override
    public void sold(int id) {
        repository.sold(id);
    }

    @Override
    public void update(Advertisement ad) {
        repository.update(ad);
    }
}

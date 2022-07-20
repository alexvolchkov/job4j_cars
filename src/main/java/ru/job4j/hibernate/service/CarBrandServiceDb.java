package ru.job4j.hibernate.service;

import org.springframework.stereotype.Service;
import ru.job4j.hibernate.entity.CarBrand;
import ru.job4j.hibernate.store.CarBrandRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarBrandServiceDb implements CarBrandService {
    private final CarBrandRepository repository;

    public CarBrandServiceDb(CarBrandRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CarBrand> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<CarBrand> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void add(CarBrand carBrand) {
        repository.add(carBrand);
    }

    @Override
    public void update(int id, CarBrand carBrand) {
        repository.update(id, carBrand);
    }

}

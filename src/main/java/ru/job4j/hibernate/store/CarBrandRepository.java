package ru.job4j.hibernate.store;

import ru.job4j.hibernate.entity.CarBrand;

import java.util.List;
import java.util.Optional;

public interface CarBrandRepository {
    List<CarBrand> findAll();

    Optional<CarBrand> findById(int id);

    void add(CarBrand carBrand);

    void update(int id, CarBrand carBrand);

}

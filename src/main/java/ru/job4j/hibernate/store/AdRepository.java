package ru.job4j.hibernate.store;

import ru.job4j.hibernate.entity.Advertisement;

import java.util.List;
import java.util.Optional;

public interface AdRepository {
    List<Advertisement> findAll();

    Optional<Advertisement> findById(int id);

    void add(Advertisement ad);

    void sold(int id);

    void update(Advertisement ad);
}

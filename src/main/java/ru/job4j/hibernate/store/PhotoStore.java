package ru.job4j.hibernate.store;

import ru.job4j.hibernate.entity.Photo;

import java.util.Optional;

public interface PhotoStore {
    Optional<Photo> findById(int id);

    void add(Photo photo);
}

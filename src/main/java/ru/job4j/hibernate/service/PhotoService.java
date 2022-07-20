package ru.job4j.hibernate.service;

import ru.job4j.hibernate.entity.Photo;

import java.util.Optional;

public interface PhotoService {

    Optional<Photo> findById(int id);

    void add(Photo photo);
}

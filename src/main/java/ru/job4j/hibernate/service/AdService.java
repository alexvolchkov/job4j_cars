package ru.job4j.hibernate.service;

import ru.job4j.hibernate.entity.Advertisement;

import java.util.List;
import java.util.Optional;

public interface AdService {

    List<Advertisement> findAll();

    Optional<Advertisement> findById(int id);

    void add(Advertisement ad);

    void sold(int id);

    void update(Advertisement ad);
}

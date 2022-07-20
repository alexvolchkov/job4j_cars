package ru.job4j.hibernate.service;

import org.springframework.stereotype.Service;
import ru.job4j.hibernate.entity.Photo;
import ru.job4j.hibernate.store.PhotoStore;

import java.util.Optional;

@Service
public class PhotoServiceDb implements PhotoService {
    private final PhotoStore photoStore;

    public PhotoServiceDb(PhotoStore photoStore) {
        this.photoStore = photoStore;
    }

    @Override
    public Optional<Photo> findById(int id) {
        return photoStore.findById(id);
    }

    @Override
    public void add(Photo photo) {
        photoStore.add(photo);
    }
}

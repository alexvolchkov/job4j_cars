package ru.job4j.hibernate.store;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.hibernate.CommonMethods;
import ru.job4j.hibernate.entity.Photo;

import java.util.Optional;

@Repository
public class PhotoStoreDb implements PhotoStore {
    private final SessionFactory sf;

    public PhotoStoreDb(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Optional<Photo> findById(int id) {
        return Optional.ofNullable(CommonMethods.tx(session -> session.get(Photo.class, id), sf));
    }

    @Override
    public void add(Photo photo) {
        CommonMethods.tx(session -> session.save(photo), sf);
    }
}

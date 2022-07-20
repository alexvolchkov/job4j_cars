package ru.job4j.hibernate.store;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.hibernate.CommonMethods;
import ru.job4j.hibernate.entity.CarBrand;

import java.util.List;
import java.util.Optional;

@Repository
public class CarBrandRepositoryDb implements CarBrandRepository {
    private final SessionFactory sf;

    public CarBrandRepositoryDb(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public List<CarBrand> findAll() {
        return CommonMethods.tx(session -> session.createQuery(
                                "select distinct cb from CarBrand cb"
                        ).list(),
                sf);
    }

    @Override
    public Optional<CarBrand> findById(int id) {
        return CommonMethods.tx(session -> session.createQuery(
                        "select distinct cb from CarBrand cb "
                                + "where cb.id = :fId"
                ).setParameter("fId", id).uniqueResultOptional(),
                sf);
    }

    @Override
    public void add(CarBrand carBrand) {
        CommonMethods.tx(session -> session.save(carBrand), sf);
    }

    @Override
    public void update(int id, CarBrand carBrand) {
        CommonMethods.tx(session -> session.createQuery(
                                "update CarBrand set name = :fName where id = :fId"
                        ).setParameter("fName", carBrand.getName())
                        .setParameter("fId", id)
                        .executeUpdate(),
                sf);
    }

}

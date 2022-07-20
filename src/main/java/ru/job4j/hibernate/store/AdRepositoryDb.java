package ru.job4j.hibernate.store;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.hibernate.CommonMethods;
import ru.job4j.hibernate.entity.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AdRepositoryDb implements AdRepository {
    private final SessionFactory sf;

    public AdRepositoryDb(SessionFactory sf) {
        this.sf = sf;
    }

    public static List<Advertisement> findAllAdLastDay(SessionFactory sf) {
        return CommonMethods.tx(session -> session.createQuery(
                                "select distinct ad from Advertisement ad "
                                        + "join fetch ad.car c "
                                        + "left join fetch c.carBrand b "
                                        + "left join fetch ad.user u "
                                        + "left join fetch ad.photo p "
                                        + "where ad.created between :dateFrom and :dateTo"
                        ).setParameter("dateFrom", LocalDateTime.now().minusDays(1).with(LocalTime.MIN))
                        .setParameter("dateTo", LocalDateTime.now())
                        .list(),
                sf);
    }

    public static List<Advertisement> findAllAdWithPhoto(SessionFactory sf) {
        return CommonMethods.tx(session -> session.createQuery(
                                "select distinct ad from Advertisement ad "
                                        + "join fetch ad.car c "
                                        + "left join fetch c.carBrand b "
                                        + "left join fetch ad.user u "
                                        + "join fetch ad.photo p "
                        ).list(),
                sf);
    }

    public static List<Advertisement> findAllAdBrand(CarBrand carBrand, SessionFactory sf) {
        return CommonMethods.tx(session -> session.createQuery(
                                "select distinct ad from Advertisement ad "
                                        + "join fetch ad.car c "
                                        + "left join fetch c.carBrand b "
                                        + "left join fetch ad.user u "
                                        + "left join fetch ad.photo p "
                                        + "where c.carBrand = :brand"
                        ).setParameter("brand", carBrand)
                        .list(),
                sf);
    }

    @Override
    public List<Advertisement> findAll() {
        return CommonMethods.tx(session -> session.createQuery(
                                "select distinct ad from Advertisement ad "
                                        + "join fetch ad.car c "
                                        + "left join fetch c.carBrand b "
                                        + "left join fetch ad.user u "
                                        + "left join fetch ad.photo p "
                        ).list(),
                sf);
    }

    @Override
    public Optional<Advertisement> findById(int id) {
        return CommonMethods.tx(session -> session.createQuery(
                        "select distinct ad from Advertisement ad "
                                + "join fetch ad.car c "
                                + "left join fetch c.carBrand b "
                                + "left join fetch c.engine e "
                                + "left join fetch ad.user u "
                                + "left join fetch ad.photo p "
                                + "where ad.id = :fId"

                ).setParameter("fId", id).uniqueResultOptional(),
                sf);
    }

    @Override
    public void add(Advertisement ad) {
        CommonMethods.tx(session -> session.save(ad), sf);
    }

    @Override
    public void sold(int id) {
        CommonMethods.tx(session -> session.createQuery(
                "update Advertisement set sold = true where id = :fId"
        ).setParameter("fId", id).executeUpdate(), sf);
    }

    @Override
    public void update(Advertisement ad) {
        CommonMethods.tx(session -> {
            session.createQuery(
                            "update Advertisement set name = :name,"
                                    + "description = :description "
                                    + "where id = :id"
                    ).setParameter("name", ad.getName())
                    .setParameter("description", ad.getDescription())
                    .setParameter("id", ad.getId()).executeUpdate();
                session.update(ad.getCar());
                return ad;
        }, sf);
    }
}

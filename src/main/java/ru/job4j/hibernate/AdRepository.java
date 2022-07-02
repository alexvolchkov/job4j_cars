package ru.job4j.hibernate;

import org.hibernate.SessionFactory;
import ru.job4j.hibernate.entity.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AdRepository {
    public static List<Advertisement> findAllAdLastDay(SessionFactory sf) {
        return CommonMethods.tx(session -> session.createQuery(
                                "select distinct ad from Advertisement ad "
                                        + "join fetch ad.car c "
                                        + "left join fetch c.carBrand b "
                                        + "left join fetch ad.author a "
                                        + "left join fetch a.user u "
                                        + "left join fetch ad.photo p "
                                        + "where ad.created between :dateFrom and :dateTo"
                        ).setParameter("dateFrom", LocalDateTime.now().minusDays(1).with(LocalTime.MIN))
                        .setParameter("dateTo", LocalDateTime.now().minusDays(1).with(LocalTime.MAX))
                        .list(),
                sf);
    }

    public static List<Advertisement> findAllAdWithPhoto(SessionFactory sf) {
        return CommonMethods.tx(session -> session.createQuery(
                                "select distinct ad from Advertisement ad "
                                        + "join fetch ad.car c "
                                        + "left join fetch c.carBrand b "
                                        + "left join fetch ad.author a "
                                        + "left join fetch a.user u "
                                        + "join fetch ad.photo p "
                        ).list(),
                sf);
    }

    public static List<Advertisement> findAllAdBrand(CarBrand carBrand, SessionFactory sf) {
        return CommonMethods.tx(session -> session.createQuery(
                                "select distinct ad from Advertisement ad "
                                        + "join fetch ad.car c "
                                        + "left join fetch c.carBrand b "
                                        + "left join fetch ad.author a "
                                        + "left join fetch a.user u "
                                        + "left join fetch ad.photo p "
                                        + "where c.carBrand = :brand"
                        ).setParameter("brand", carBrand)
                        .list(),
                sf);
    }
}

package ru.job4j.hibernate.store;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.hibernate.CommonMethods;
import ru.job4j.hibernate.entity.Engine;

import java.util.List;
import java.util.Optional;

@Repository
public class EngineRepositoryDb implements EngineRepository {
    private final SessionFactory sf;

    public EngineRepositoryDb(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public List<Engine> findAll() {
        return CommonMethods.tx(session -> session.createQuery(
                                "select distinct e from Engine e"
                        ).list(),
                sf);
    }

    @Override
    public Optional<Engine> findById(int id) {
        return CommonMethods.tx(session -> session.createQuery(
                        "select distinct e from Engine e "
                                + "where e.id = :fId"
                ).setParameter("fId", id).uniqueResultOptional(),
                sf);
    }

    @Override
    public void add(Engine engine) {
        CommonMethods.tx(session -> session.save(engine), sf);
    }

    @Override
    public void update(int id, Engine engine) {
        CommonMethods.tx(session -> session.createQuery(
                                "update Engine set name = :fName where id = :fId"
                        ).setParameter("fName", engine.getName())
                        .setParameter("fId", id)
                        .executeUpdate(),
                sf);
    }

}

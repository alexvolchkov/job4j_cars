package ru.job4j.hibernate.store;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.hibernate.CommonMethods;
import ru.job4j.hibernate.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryDb implements UserRepository {
    private final SessionFactory sf;

    public UserRepositoryDb(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public List<User> findAll() {
        return CommonMethods.tx(session -> session.createQuery(
                                "select distinct u from User u"
                        ).list(),
                sf);
    }

    @Override
    public Optional<User> add(User user) {
        Optional<User> rsl = Optional.empty();
        try {
            CommonMethods.tx(
                    session -> {
                        session.save(user);
                        return user;
                    },
                    sf);
            rsl = Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Optional<User> findUserByNameAndPwd(String name, String password) {
        return CommonMethods.tx(session ->
                session.createQuery("select distinct u from User u "
                                + "where name = :fName and password = :fPassword")
                        .setParameter("fName", name)
                        .setParameter("fPassword", password)
                        .uniqueResultOptional(), sf);
    }
}

package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Optional;

@Repository
public class UserStore {
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class.getName());

    private final SessionFactory sf;

    public UserStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<User> add(User user) {
        Optional<User> optional = Optional.empty();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            optional = Optional.of(user);
            session.save(optional);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return optional;
    }

    public Optional<User> findUserByLoginAndPwd(String login, String password) {
        Optional optional = Optional.empty();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            optional = session.createQuery(
                    "from User where login = :fLogin and password = :fPassword")
                    .setParameter("fLogin", login)
                    .setParameter("fPassword", password)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return optional;
    }
}

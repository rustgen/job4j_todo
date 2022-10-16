package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskStore {

    private static final Logger LOG = LoggerFactory.getLogger(TaskStore.class.getName());

    private final SessionFactory sf;

    public TaskStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Task add(Task task) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            task.setCreated(LocalDateTime.now());
            task.setCompleted(false);
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return task;
    }

    public void update(int id, Task task) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            task.setId(id);
            task.setCreated(LocalDateTime.now());
            session.update(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void delete(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE Task WHERE id = :fId")
                    .setParameter("fId", id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public List<Task> findAll() {
        List result = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("FROM Task t ORDER BY t.id ASC")
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public List<Task> findByName(String key) {
        List result = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query query = sf.getCurrentSession()
                    .createQuery("FROM Task t WHERE t.name = :fKey");
            result = query.setParameter("fKey", key).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public Task findById(int id) {
        Task result = null;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.get(Task.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public List<Task> getStatuses(boolean completed) {
        List result = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query query = session
                    .createQuery("FROM Task t WHERE t.completed = :fCompleted");
            result = query.setParameter("fCompleted", completed).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public void completedId(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery("UPDATE Task t SET t.completed = true WHERE t.id = :fId").
                    setParameter("fId", id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

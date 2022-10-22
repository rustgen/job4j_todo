package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskRepository {

    private final CrudRepository crudRepository;

    public Optional<Task> add(Task task) {
        crudRepository.run(session -> session.persist(task));
        return Optional.of(task);
    }

    public void update(int id, Task task) {
        crudRepository.run("UPDATE Task SET name = :fName, description = :fDescription WHERE id = :fId",
                Map.of("fName", task.getName(), "fDescription", task.getDescription(), "fId", id));
    }

    public void delete(int id) {
        crudRepository.run("DELETE Task WHERE id = :fId",
                Map.of("fId", id));
    }

    public List<Task> findAll() {
        return crudRepository.query("FROM Task JOIN FETCH priority",
                Task.class);
    }

    public List<Task> findByName(String key) {
        return crudRepository.query("FROM Task t WHERE t.name like :fKey",
                Task.class,
                Map.of("fKey", "%" + key + "%"));
    }

    public Optional<Task> findById(int id) {
        return crudRepository.optional("FROM Task WHERE id = :fId",
                Task.class,
                Map.of("fId", id));
    }

    public List<Task> getStatuses(boolean completed) {
        return crudRepository.query("FROM Task t JOIN FETCH t.priority WHERE t.completed = :fCompleted",
                Task.class,
                Map.of("fCompleted", completed));
    }

    public void completedId(int id) {
        crudRepository.run("UPDATE Task t SET t.completed = true WHERE t.id = :fId",
                Map.of("fId", id));
    }
}

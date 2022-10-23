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

    private static final String UPDATE = """
            UPDATE Task SET name = :fName, description = :fDescription, priority = :fPriority WHERE id = :fId
            """;
    private static final String DELETE = """
            DELETE Task WHERE id = :fId
            """;
    private static final String FIND_ALL = """
            FROM Task t JOIN FETCH t.priority
            """;
    private static final String FIND_BY_NAME = """
            FROM Task t WHERE t.name like :fKey
            """;
    private static final String FIND_BY_ID = """
            FROM Task t JOIN FETCH t.priority WHERE t.id = :fId
            """;
    private static final String GET_STATUSES = """
            FROM Task t JOIN FETCH t.priority WHERE t.completed = :fCompleted
            """;
    private static final String COMPLETED_ID = """
            UPDATE Task t SET t.completed = true WHERE t.id = :fId
            """;

    private final CrudRepository crudRepository;

    public Optional<Task> add(Task task) {
        crudRepository.run(session -> session.persist(task));
        return Optional.of(task);
    }

    public void update(int id, Task task) {
        crudRepository.run(UPDATE,
                Map.of("fName", task.getName(), "fDescription", task.getDescription(),
                        "fPriority", task.getPriority(), "fId", id));
    }

    public void delete(int id) {
        crudRepository.run(DELETE, Map.of("fId", id));
    }

    public List<Task> findAll() {
        return crudRepository.query(FIND_ALL, Task.class);
    }

    public List<Task> findByName(String key) {
        return crudRepository.query(FIND_BY_NAME, Task.class,
                Map.of("fKey", "%" + key + "%"));
    }

    public Optional<Task> findById(int id) {
        return crudRepository.optional(FIND_BY_ID, Task.class,
                Map.of("fId", id));
    }

    public List<Task> getStatuses(boolean completed) {
        return crudRepository.query(GET_STATUSES, Task.class,
                Map.of("fCompleted", completed));
    }

    public void completedId(int id) {
        crudRepository.run(COMPLETED_ID, Map.of("fId", id));
    }
}

package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.TaskStore;

import java.util.List;

@Service
public class TaskService {

    private final TaskStore store;

    public TaskService(TaskStore store) {
        this.store = store;
    }

    public Task add(Task task) {
        return store.add(task);
    }

    public void update(int id, Task task) {
        store.update(id, task);
    }

    public void delete(int id) {
        store.delete(id);
    }

    public List<Task> findAll() {
        return store.findAll();
    }

    public List<Task> findByName(String key) {
        return store.findByName(key);
    }

    public Task findById(int id) {
        return store.findById(id);
    }

    public List<Task> completed() {
        return store.getStatuses(true);
    }

    public List<Task> notCompleted() {
        return store.getStatuses(false);
    }

    public void completedId(int id) {
        store.completedId(id);
    }
}

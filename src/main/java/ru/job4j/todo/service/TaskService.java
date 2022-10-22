package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.PriorityStore;
import ru.job4j.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@ThreadSafe
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;
    private final PriorityStore priorityStore;

    public Optional<Task> add(Task task) {
        return taskRepository.add(task);
    }

    public void update(int id, Task task) {
        taskRepository.update(id, task);
    }

    public void delete(int id) {
        taskRepository.delete(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByName(String key) {
        return taskRepository.findByName(key);
    }

    public Optional<Task> findById(int id) {
        return taskRepository.findById(id);
    }

    public List<Task> completed() {
        return taskRepository.getStatuses(true);
    }

    public List<Task> notCompleted() {
        return taskRepository.getStatuses(false);
    }

    public void completedId(int id) {
        taskRepository.completedId(id);
    }

    public List<Priority> findAllPriorities() {
        return priorityStore.findAllPriorities();
    }

    public Optional<Priority> findByIdPriority(int id) {
        return priorityStore.findByIdPriority(id);
    }
}

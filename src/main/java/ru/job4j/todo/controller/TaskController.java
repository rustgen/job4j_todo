package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.TaskService;

import javax.servlet.http.HttpSession;

import java.util.NoSuchElementException;

import static ru.job4j.todo.util.UserSession.getSession;

@Controller
@ThreadSafe
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/tasks")
    public String tasks(Model model, HttpSession session) {
        model.addAttribute("tasks", service.findAll());
        getSession(model, session);
        return "tasks";
    }

    @GetMapping("/formAddTask")
    public String addTask(Model model, HttpSession session) {
        getSession(model, session);
        return "addTask";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute Task task, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        task.setUser(user);
        service.add(task);
        getSession(model, session);
        return "redirect:/tasks";
    }

    @GetMapping("/taskInfo/{taskId}")
    public String taskInfo(Model model, HttpSession session, @PathVariable("taskId") int id) {
        model.addAttribute("task",
                service.findById(id).orElseThrow(() -> new NoSuchElementException("No element")));
        getSession(model, session);
        return "taskInfo";
    }

    @GetMapping("/completedTasks/{taskId}")
    public String completedId(@PathVariable("taskId") int id, Model model, HttpSession session) {
        service.completedId(id);
        getSession(model, session);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{taskId}")
    public String delete(@PathVariable("taskId") int id, Model model, HttpSession session) {
        service.delete(id);
        getSession(model, session);
        return "redirect:/tasks";
    }

    @GetMapping("/formUpdateTask/{taskId}")
    public String formUpdateTask(Model model, HttpSession session, @PathVariable("taskId") int id) {
        model.addAttribute("task", service.findById(id));
        getSession(model, session);
        return "updateTask";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        task.setUser(user);
        service.update(task.getId(), task);
        getSession(model, session);
        return "redirect:/tasks";
    }

    @GetMapping("/completedTasks")
    public String completedTasks(Model model, HttpSession session) {
        model.addAttribute("tasks", service.completed());
        getSession(model, session);
        return "completedTasks";
    }

    @GetMapping("/newTasks")
    public String newTasks(Model model, HttpSession session) {
        model.addAttribute("tasks", service.notCompleted());
        getSession(model, session);
        return "newTasks";
    }
}

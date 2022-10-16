package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@Controller
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", service.findAll());
        return "tasks";
    }

    @GetMapping("/formAddTask")
    public String addTask(Model model) {
        return "addTask";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute Task task) {
        service.add(task);
        return "redirect:/tasks";
    }

    @GetMapping("/taskInfo/{taskId}")
    public String taskInfo(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", service.findById(id));
        return "taskInfo";
    }

    @GetMapping("/completedTasks/{taskId}")
    public String completedId(@PathVariable("taskId") int id) {
        service.completedId(id);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{taskId}")
    public String delete(@PathVariable("taskId") int id) {
        service.delete(id);
        return "redirect:/tasks";
    }

    @GetMapping("/formUpdateTask/{taskId}")
    public String formUpdateTask(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", service.findById(id));
        return "updateTask";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task) {
        service.update(task.getId(), task);
        return "redirect:/tasks";
    }

    @GetMapping("/completedTasks")
    public String completedTasks(Model model) {
        model.addAttribute("tasks", service.completed());
        return "completedTasks";
    }

    @GetMapping("/newTasks")
    public String newTasks(Model model) {
        model.addAttribute("tasks", service.notCompleted());
        return "newTasks";
    }
}

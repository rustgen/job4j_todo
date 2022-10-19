package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static ru.job4j.todo.util.UserSession.getSession;

@Controller
@ThreadSafe
public class IndexController {

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        getSession(model, session);
        return "index";
    }
}

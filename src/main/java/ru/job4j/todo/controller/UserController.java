package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static ru.job4j.todo.util.UserSession.getSession;

@Controller
@AllArgsConstructor
@ThreadSafe
public class UserController {

    private final UserService userService;

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail,
                            HttpSession session) {
        model.addAttribute("fail", fail != null);
        getSession(model, session);
        return "login";
    }

    @GetMapping("/signUp")
    public String signUpPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail,
                             HttpSession session) {
        model.addAttribute("fail", fail != null);
        getSession(model, session);
        return "signUp";
    }

    @PostMapping("/login")
    public String loginPage(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userOpt = userService.findUserByLoginAndPassword(
                user.getLogin(), user.getPassword()
        );
        if (userOpt.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userOpt.get());
        return "redirect:/index";
    }

    @PostMapping("/signUp")
    public String login(@ModelAttribute User user, Model model, HttpSession session) {
        User added = userService.add(user);
        if (added == null) {
            return "redirect:/signUp?fail=true";
        }
        getSession(model, session);
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPage";
    }
}

package ru.job4j.todo.util;

import lombok.NoArgsConstructor;
import org.springframework.ui.Model;
import ru.job4j.todo.model.User;

import javax.servlet.http.HttpSession;

@NoArgsConstructor
public final class UserSession {

    public static void getSession(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Guest");
        }
        model.addAttribute("user", user);
    }
}


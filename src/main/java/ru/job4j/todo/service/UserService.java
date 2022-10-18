package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.UserStore;

import java.util.Optional;

@Service
public class UserService {

    private UserStore store;

    public UserService(UserStore store) {
        this.store = store;
    }

    public Optional<User> add(User user) {
        return store.add(user);
    }

    public Optional<User> findUserByLoginAndPwd(String login, String password) {
        return store.findUserByLoginAndPwd(login, password);
    }
}

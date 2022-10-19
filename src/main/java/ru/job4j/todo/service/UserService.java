package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserRepository;
import ru.job4j.todo.store.UserStore;

import java.util.Optional;

@Service
@ThreadSafe
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User add(User user) {
        return userRepository.create(user);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return userRepository.findUserByLoginAndPassword(login, password);
    }
}

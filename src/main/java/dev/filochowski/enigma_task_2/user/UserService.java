package dev.filochowski.enigma_task_2.user;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);
    List<User> getUsers();
    User saveUser(User user);
    boolean deleteUser(Long id);
    Optional<User> updateUser(Long id, User user);
}

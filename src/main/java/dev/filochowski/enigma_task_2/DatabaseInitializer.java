package dev.filochowski.enigma_task_2;

import dev.filochowski.enigma_task_2.user.User;
import dev.filochowski.enigma_task_2.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
@Profile("!test")
public class DatabaseInitializer implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        List<User> users = new ArrayList<>();
        users.add(new User("Maciej", "Filochowski", "contact@filochowski.dev"));
        users.add(new User("Krzysztof", "Kowalski", "kowalski@example.com"));
        users.add(new User("Jan", "Nowak", "nowak@example.com"));

        userRepository.saveAll(users);

    }
}

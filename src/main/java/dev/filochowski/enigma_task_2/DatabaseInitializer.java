package dev.filochowski.enigma_task_2;

import dev.filochowski.enigma_task_2.task.Status;
import dev.filochowski.enigma_task_2.task.Task;
import dev.filochowski.enigma_task_2.task.TaskRepository;
import dev.filochowski.enigma_task_2.user.User;
import dev.filochowski.enigma_task_2.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
@Profile("!test")
public class DatabaseInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) {
        List<User> users = new ArrayList<>();
        users.add(new User("Maciej", "Filochowski", "contact@filochowski.dev"));
        users.add(new User("Krzysztof", "Kowalski", "kowalski@example.com"));
        users.add(new User("Jan", "Nowak", "nowak@example.com"));

        userRepository.saveAll(users);

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Solve recruitment task", "", Status.DONE, LocalDate.of(2024, Month.NOVEMBER, 20)));
        tasks.add(new Task("Estimate securing endpoints", "Estimate adding JWT auth to the app", Status.ON_HOLD, LocalDate.of(2024, Month.DECEMBER, 1)));
        tasks.add(new Task("Create documentation", "Document all API endpoints", Status.TO_DO, LocalDate.of(2025, Month.JANUARY, 7)));

        taskRepository.saveAll(tasks);
    }
}

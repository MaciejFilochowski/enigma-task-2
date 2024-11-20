package dev.filochowski.enigma_task_2.task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> getTask(Long id);
    List<Task> getTasks();
    Task saveTask(Task task);
    boolean deleteTask(Long id);
    Optional<Task> updateTask(Long id, Task task);
}

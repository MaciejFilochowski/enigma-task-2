package dev.filochowski.enigma_task_2.task;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> getTask(Long id) {
        log.info("Fetching task by id: {}", id);
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getTasks() {
        log.info("Fetching all tasks");
        return taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public boolean deleteTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            return false;
        }

        taskRepository.delete(optionalTask.get());
        return true;
    }


    @Override
    public Optional<Task> updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            return Optional.empty();
        }

        Task updatedTask = optionalTask.get();
        if (task.getTitle() != null) {
            updatedTask.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            updatedTask.setDescription(task.getDescription());
        }
        if (task.getStatus() != null) {
            updatedTask.setStatus(task.getStatus());
        }
        if (task.getDueDate() != null) {
            updatedTask.setDueDate(task.getDueDate());
        }

        return Optional.of(taskRepository.save(updatedTask));
    }
}
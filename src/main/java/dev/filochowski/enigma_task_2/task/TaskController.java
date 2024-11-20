package dev.filochowski.enigma_task_2.task;

import dev.filochowski.enigma_task_2.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = taskService.getTasks();

        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable("taskId") Long id) {
        Optional<Task> optionalTask = taskService.getTask(id);

        if (optionalTask.isEmpty()) {
            throw new NotFoundException();
        }

        Task task = optionalTask.get();

        return ResponseEntity.ok().body(task);
    }

    @PostMapping
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        Task createdTask = taskService.saveTask(task);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/tasks/" + createdTask.getId()).toUriString());
        return ResponseEntity.created(uri).body(createdTask);
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("taskId") Long id) {
        boolean isPresent = taskService.deleteTask(id);

        if (!isPresent) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{taskId}")
    public ResponseEntity<Task> createOrUpdateTask(@PathVariable("taskId") Long id, @RequestBody Task request) {
        Optional<Task> optionalTask = taskService.updateTask(id, request);

        if (optionalTask.isEmpty()) {
            Task createdTask = taskService.saveTask(request);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/tasks/" + createdTask.getId()).toUriString());
            return ResponseEntity.created(uri).body(createdTask);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{taskId}")
    public ResponseEntity<HttpStatus> partialUpdateTask(@PathVariable("taskId") Long id, @RequestBody Task request) {
        Optional<Task> optionalTask = taskService.updateTask(id, request);

        if (optionalTask.isEmpty()){
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

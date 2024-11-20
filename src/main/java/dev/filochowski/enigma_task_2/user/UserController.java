package dev.filochowski.enigma_task_2.user;

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
@RequestMapping(path = "api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long id) {
        Optional<User> optionalUser = userService.getUser(id);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException();
        }

        User user = optionalUser.get();

        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/" + createdUser.getId()).toUriString());
        return ResponseEntity.created(uri).body(createdUser);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") Long id) {
        boolean isPresent = userService.deleteUser(id);

        if (!isPresent) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{userId}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("userId") Long id, @RequestBody User request) {
        Optional<User> user = userService.updateUser(id, request);

        if (user.isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

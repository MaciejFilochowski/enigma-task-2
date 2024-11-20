package dev.filochowski.enigma_task_2.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class NotFoundException extends RuntimeException {

}

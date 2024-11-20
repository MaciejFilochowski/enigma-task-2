package dev.filochowski.enigma_task_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnigmaTask2Application {

	public static void main(String[] args) {
		SpringApplication application =
				new SpringApplication(EnigmaTask2Application.class);
		application.run(args);
	}
}

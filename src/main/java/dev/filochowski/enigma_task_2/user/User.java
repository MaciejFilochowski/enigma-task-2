package dev.filochowski.enigma_task_2.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            updatable = false)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(
            nullable = false,
            unique = true
    )
    private String email;

    public User(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}

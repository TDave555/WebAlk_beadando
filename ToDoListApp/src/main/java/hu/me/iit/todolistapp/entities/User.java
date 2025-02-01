package hu.me.iit.todolistapp.entities;

import hu.me.iit.todolistapp.types.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "todoOwner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Todo> todos;

    @OneToMany(mappedBy = "categoryOwner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> categories;

     public User(String username, String password, String role) {
         this.username = username;
         this.password = password;
         this.role = Role.valueOf(role);
     }

}

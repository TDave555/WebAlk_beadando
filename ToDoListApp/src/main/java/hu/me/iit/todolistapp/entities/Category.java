package hu.me.iit.todolistapp.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User categoryOwner;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Todo> todosInCategory;
}

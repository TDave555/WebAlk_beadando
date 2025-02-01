package hu.me.iit.todolistapp.repositories;

import hu.me.iit.todolistapp.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}

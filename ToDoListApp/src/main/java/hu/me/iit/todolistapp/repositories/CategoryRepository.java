package hu.me.iit.todolistapp.repositories;

import hu.me.iit.todolistapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

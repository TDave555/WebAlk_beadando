package hu.me.iit.todolistapp.controllers;

import hu.me.iit.todolistapp.dtos.TodoDto;
import hu.me.iit.todolistapp.services.TodosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/todos")
public class TodosController {
    private final TodosService todosService;

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        return ResponseEntity.ok(todosService.getAllTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id) {
        return ResponseEntity.ok(todosService.getTodo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoDto todoDto) {
        TodoDto updatedTodo = todosService.updateTodo(id, todoDto);
        return ResponseEntity.ok(updatedTodo);
    }

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@Valid @RequestBody TodoDto todoDto) {
        TodoDto createdTodo = todosService.createTodo(todoDto);
        return ResponseEntity.created(URI.create("/todos/" + createdTodo.getId())).body(createdTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todosService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package hu.me.iit.todolistapp.services;

import hu.me.iit.todolistapp.dtos.TodoDto;
import hu.me.iit.todolistapp.entities.Todo;
import hu.me.iit.todolistapp.exceptions.AppException;
import hu.me.iit.todolistapp.mappers.TodoMapper;
import hu.me.iit.todolistapp.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodosService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todoMapper.toTodoDtos(todos);
    }

    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new AppException("To-Do not found", HttpStatus.NOT_FOUND));
        return todoMapper.toTodoDto(todo);
    }

    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo todo= todoMapper.toTodo(todoDto);
        Todo storedTodo = todoRepository.findById(id).orElseThrow(() -> new AppException("To-Do not found", HttpStatus.NOT_FOUND));
        todo.setId(storedTodo.getId());
        Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.toTodoDto(updatedTodo);
    }

    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = todoMapper.toTodo(todoDto);
        Todo createdTodo = todoRepository.save(todo);
        return todoMapper.toTodoDto(createdTodo);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}

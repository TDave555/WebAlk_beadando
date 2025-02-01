package hu.me.iit.todolistapp.mappers;

import hu.me.iit.todolistapp.dtos.TodoDto;
import hu.me.iit.todolistapp.entities.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo toTodo(TodoDto todoDto);
    TodoDto toTodoDto(Todo todo);
    List<TodoDto> toTodoDtos(List<Todo> todos);

}

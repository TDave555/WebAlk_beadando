package hu.me.iit.todolistapp.dtos;

import hu.me.iit.todolistapp.types.Role;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private Role role;
    private List<TodoDto> todos;
    private List<CategoryDto> categories;
}

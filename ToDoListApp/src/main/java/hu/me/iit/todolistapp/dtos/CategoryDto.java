package hu.me.iit.todolistapp.dtos;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;
    private UserDto categoryOwner;
    private List<TodoDto> todosInCategory;
}

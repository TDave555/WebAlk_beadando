package hu.me.iit.todolistapp.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class TodoDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime deadLine;
    private UserDto todoOwner;
    private List<CategoryDto>  categories;
}

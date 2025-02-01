package hu.me.iit.todolistapp.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}

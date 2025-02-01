package hu.me.iit.todolistapp.controllers;

import hu.me.iit.todolistapp.dtos.LoginRequest;
import hu.me.iit.todolistapp.dtos.UserDto;
import hu.me.iit.todolistapp.entities.User;
import hu.me.iit.todolistapp.mappers.UserMapper;
import hu.me.iit.todolistapp.services.UsersService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsersService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userService.getUserByUsername(authentication.getName());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Username not found");
            } else if (user.getPassword().equals(loginRequest.getPassword()) == false) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid password");
            } else {
                UserDto userDto = userMapper.toUserDto(user);
                return ResponseEntity.status(HttpStatus.FOUND).body(userDto);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during login");
        }
    }
}

package hu.me.iit.todolistapp.controllers;


import hu.me.iit.todolistapp.dtos.UserDto;
import hu.me.iit.todolistapp.entities.User;
import hu.me.iit.todolistapp.mappers.UserMapper;
import hu.me.iit.todolistapp.services.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/users")
//@PreAuthorize("hasRole('ADMIN')")
public class UsersController {
    private final UsersService usersService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> allUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.getUser(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = usersService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = usersService.createUser(userDto);
        return ResponseEntity.created(URI.create("api/admin/users/" + createdUser.getId())).body(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/current-user-role")
    public ResponseEntity<String> getCurrentUserRole(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDto userDto = userMapper.toUserDto((User) authentication.getPrincipal());
            return ResponseEntity.ok(userDto.getRole().name());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
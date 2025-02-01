package hu.me.iit.todolistapp.services;

import hu.me.iit.todolistapp.dtos.UserDto;
import hu.me.iit.todolistapp.entities.User;
import hu.me.iit.todolistapp.exceptions.AppException;
import hu.me.iit.todolistapp.mappers.UserMapper;
import hu.me.iit.todolistapp.repositories.UserRepository;
import hu.me.iit.todolistapp.types.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDtos(users);
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userMapper.toUserDto(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username +" not found"));
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user= userMapper.toUser(userDto);
        User storedUser = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        user.setId(storedUser.getId());
        User updatedUser = userRepository.save(user);
        return userMapper.toUserDto(updatedUser);
    }

    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Set default role if not provided
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

package hu.me.iit.todolistapp.services;

import hu.me.iit.todolistapp.config.UserDetailsConfig;
import hu.me.iit.todolistapp.entities.User;
import hu.me.iit.todolistapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserEntityUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
public UserDetails loadUserByUsername(String username) {
        Optional<User> user = repository.findByUsername(username);
        user.map(UserDetailsConfig::new)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username +" not found"));
        return null;
    }
}

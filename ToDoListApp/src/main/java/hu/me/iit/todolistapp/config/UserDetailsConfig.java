package hu.me.iit.todolistapp.config;

import hu.me.iit.todolistapp.entities.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsConfig implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserDetailsConfig(User user) {
        username = user.getUsername();
        password = user.getPassword();
        List<String> roles = new ArrayList<String>();
        switch (user.getRole().name()) {
            case "ADMIN":
                roles.add("ADMIN");
                roles.add("USER");
                break;
            case "USER":
                roles.add("USER");
                break;
        }
        authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

package hu.me.iit.todolistapp.mappers;

import hu.me.iit.todolistapp.dtos.UserDto;
import hu.me.iit.todolistapp.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
    List<UserDto> toUserDtos(List<User> users);

}

package pl.coderslab.charity.controller.mapper;

import pl.coderslab.charity.controller.dto.UserDto;
import pl.coderslab.charity.model.User;

public class UserDtoMapper {
    public static UserDto mapUserToUserDto(User user) {
        return new UserDto(
                null,
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                null
        );
    }
}

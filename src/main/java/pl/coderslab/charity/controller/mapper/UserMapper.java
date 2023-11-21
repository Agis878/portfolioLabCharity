package pl.coderslab.charity.controller.mapper;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.controller.dto.RegisterDto;
import pl.coderslab.charity.model.User;

@Component
public class UserMapper {
    public static User mapToUser(RegisterDto userDTO) {
        return User.builder()
                .username(userDTO.username())
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .password(userDTO.password())
                .build();
    }
}

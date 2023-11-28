package pl.coderslab.charity.model.mapper;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.dto.RegisterDto;

@Component
public class UserMapper {
    public static User mapToUser(RegisterDto userDTO, String activationCode) {
        return User.builder()
                .username(userDTO.username())
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .password(userDTO.password())
                .activationCode(activationCode)
                .build();
    }
}

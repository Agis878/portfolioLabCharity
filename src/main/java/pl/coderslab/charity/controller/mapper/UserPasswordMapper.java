package pl.coderslab.charity.controller.mapper;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.controller.dto.PasswordUpdateDto;
import pl.coderslab.charity.model.User;

@Component
public class UserPasswordMapper {
    public static User mapToUserPassword(PasswordUpdateDto passwordUpdateDto) {
        return User.builder()

                .password(passwordUpdateDto.password())
                .build();
    }
}

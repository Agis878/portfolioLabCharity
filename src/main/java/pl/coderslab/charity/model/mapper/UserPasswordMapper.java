package pl.coderslab.charity.model.mapper;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.dto.PasswordUpdateDto;

@Component
public class UserPasswordMapper {
    public static User mapToUserPassword(PasswordUpdateDto passwordUpdateDto) {
        return User.builder()
                .password(passwordUpdateDto.password())
                .build();
    }
}

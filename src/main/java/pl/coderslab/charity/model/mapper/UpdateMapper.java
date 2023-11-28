package pl.coderslab.charity.model.mapper;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.dto.UpdateDto;

@Component
public class UpdateMapper {
    public static User mapToUpdateUser(UpdateDto updateDto) {
        return User.builder()
                .username(updateDto.username())
                .firstName(updateDto.firstName())
                .lastName(updateDto.lastName())
                .build();
    }
}

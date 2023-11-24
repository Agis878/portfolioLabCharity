package pl.coderslab.charity.controller.mapper;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.controller.dto.UpdateDto;
import pl.coderslab.charity.model.User;

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

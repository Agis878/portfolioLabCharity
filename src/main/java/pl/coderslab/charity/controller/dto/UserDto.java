package pl.coderslab.charity.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record UserDto(
        Long id,
        @Email
        @NotBlank
        String username,
        String firstName,
        String lastName,
        @NotBlank
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, and one digit.")
        String password,
        String passwordConfirm
) {

}

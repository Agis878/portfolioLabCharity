package pl.coderslab.charity.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UpdateDto(
        @Email
        @NotBlank
        String username,
        String firstName,
        String lastName
) {
}

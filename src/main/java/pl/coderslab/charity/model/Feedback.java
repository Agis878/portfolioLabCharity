package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Feedback {
    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;
    private String message;
}

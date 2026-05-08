package com.Munch.Backend.User.Controller.public_user_controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {

    @NotBlank(message = "Name can't be blank")
    private String username;

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password can't be blank")
    private String password;
}

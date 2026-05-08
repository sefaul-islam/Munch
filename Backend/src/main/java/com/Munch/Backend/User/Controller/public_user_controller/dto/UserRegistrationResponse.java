package com.Munch.Backend.User.Controller.public_user_controller.dto;

import com.Munch.Backend.User.Entity.User;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRegistrationResponse {

    private final UUID id;
    private final String username;
    private final String email;
    private final User.UserStatus status;
    private final LocalDateTime userRegistrationDate;
}

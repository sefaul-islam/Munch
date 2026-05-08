package com.Munch.Backend.User.Controller.public_user_controller;

import com.Munch.Backend.User.Controller.public_user_controller.dto.UserRegistrationRequest;
import com.Munch.Backend.User.Controller.public_user_controller.dto.UserRegistrationResponse;
import com.Munch.Backend.User.Service.PublicUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/users")
public class PublicUserController {

    private final PublicUserService publicUserService;

    public PublicUserController(PublicUserService publicUserService) {
        this.publicUserService = publicUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> register(@Valid @RequestBody UserRegistrationRequest request) {
        UserRegistrationResponse response = publicUserService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

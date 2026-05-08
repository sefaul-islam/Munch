package com.Munch.Backend.User.Service;

import com.Munch.Backend.User.Controller.public_user_controller.dto.UserRegistrationRequest;
import com.Munch.Backend.User.Controller.public_user_controller.dto.UserRegistrationResponse;
import com.Munch.Backend.User.Entity.Role;
import com.Munch.Backend.User.Entity.RoleName;
import com.Munch.Backend.User.Entity.User;
import com.Munch.Backend.User.Repository.RoleRepository;
import com.Munch.Backend.User.Repository.UserRepository;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PublicUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public PublicUserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public UserRegistrationResponse register(UserRegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        Role role = roleRepository.findByName(RoleName.CUSTOMER)
                .orElseGet(() -> roleRepository.save(new Role(RoleName.CUSTOMER)));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUserRegistrationDate(LocalDateTime.now());

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        User saved = userRepository.save(user);

        return new UserRegistrationResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getStatus(),
                saved.getUserRegistrationDate()
        );
    }
}


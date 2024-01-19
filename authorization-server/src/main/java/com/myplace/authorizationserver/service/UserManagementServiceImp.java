package com.myplace.authorizationserver.service;

import com.myplace.authorizationserver.ErrorHandling.DataBaseInteractionException;
import com.myplace.authorizationserver.entity.UserCredentials;
import com.myplace.authorizationserver.models.InternalConfirmationDTO;
import com.myplace.authorizationserver.models.UserCredentialDTO;
import com.myplace.authorizationserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManagementServiceImp {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InternalConfirmationDTO<UserCredentials> createUserCredentials(UserCredentialDTO user) {
        Optional<UserCredentials> newSetOfCredentials =  Optional.of(userRepository.save(mapToUserCredentials(user)));
        return newSetOfCredentials
                .map(elem -> new InternalConfirmationDTO<>(
                        "User credentials created sucessfully",
                        true,
                        elem))
                .orElseThrow( () -> new DataBaseInteractionException("Unable to store user credentials"));
    }
    private UserCredentials mapToUserCredentials(UserCredentialDTO dto){
        return UserCredentials.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(dto.getRoles())
                .build();
    }

}


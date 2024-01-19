package com.myplace.authorizationserver.controller;

import com.myplace.authorizationserver.entity.UserCredentials;
import com.myplace.authorizationserver.models.InternalConfirmationDTO;
import com.myplace.authorizationserver.models.UserCredentialDTO;
import com.myplace.authorizationserver.service.UserManagementServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorizationManagementApi {

    private final UserManagementServiceImp userManagementServiceImp;

    @PostMapping ("/create-usercredentials")
    public ResponseEntity<InternalConfirmationDTO<UserCredentials>> createNewUserCredentials(@RequestBody UserCredentialDTO userCredentials){
        var confirmationDto = userManagementServiceImp.createUserCredentials(userCredentials);
        return ResponseEntity.ok(confirmationDto);
    }
    @GetMapping("/")
    public String hey(){
        return " Hey";
    }
}

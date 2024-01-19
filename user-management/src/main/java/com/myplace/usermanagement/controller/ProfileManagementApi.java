package com.myplace.usermanagement.controller;

import com.myplace.usermanagement.errors.ErrorTypes.ValidationException;
import com.myplace.usermanagement.models.InternalConfirmationDTO;
import com.myplace.usermanagement.models.MemberProfileDTO;
import com.myplace.usermanagement.services.ProfileServiceImp;
import com.myplace.usermanagement.validateData.ProfileManagementValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController("/v1/profile")
@RequiredArgsConstructor
public class ProfileManagementApi {
    private final ProfileServiceImp profileServiceImp;
    private final ProfileManagementValidator profileValidator;
    @InitBinder
    public void setValidators(WebDataBinder binder){
        binder.addValidators(profileValidator);
    }

    @PutMapping (path = "update-profile")
    public ResponseEntity<InternalConfirmationDTO<MemberProfileDTO>> updateProfileInfo(
            @Valid @RequestBody
            MemberProfileDTO profileUpdate, BindingResult errorCheck, Authentication auth)
    {
        if (errorCheck.hasErrors()) {
            throw new ValidationException("Information not valid", errorCheck );
        } else {
            var confirmationDTO = profileServiceImp.insertUpdateMemberInfo(String.valueOf(auth.getPrincipal()) ,profileUpdate);
            return ResponseEntity.ok(confirmationDTO);
        }
    }
    @GetMapping("/retrieve-userinfo")
    public ResponseEntity<InternalConfirmationDTO<MemberProfileDTO>> fetchMemberInfo(Authentication auth){
        InternalConfirmationDTO<MemberProfileDTO> confirmationDTO = profileServiceImp.getMemberInfo(String.valueOf(auth.getPrincipal()));
        return ResponseEntity.ok(confirmationDTO);
    }


}

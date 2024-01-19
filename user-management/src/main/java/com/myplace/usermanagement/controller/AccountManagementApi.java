package com.myplace.usermanagement.controller;

import com.myplace.usermanagement.errors.ErrorTypes.ValidationException;
import com.myplace.usermanagement.models.*;
import com.myplace.usermanagement.validateData.AccountManagementValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.myplace.usermanagement.services.AccountServiceImp;

@RestController
@RequestMapping(path = "/v1/account")
@RequiredArgsConstructor
public class AccountManagementApi {
  private final Logger logger = LoggerFactory.getLogger(AccountManagementApi.class);
  private final AccountServiceImp accountService;
  private final AccountManagementValidator validator;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.addValidators(validator);
  }

  @GetMapping("/please")
  public String please(){
    return "SAVE ME";
  }

  @PostMapping("/verify-person")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<String> verifyPerson(){
    return null;
  }

  @PostMapping("/")
  @PreAuthorize("hasRole('USER')")
  public String changePassword(@RequestBody PasswordModelDTO passwordModelDTO, Authentication auth){
    return null;
  }

  @GetMapping(value = "email-check", params = {"email"}, produces = {"application/json"})
  public ResponseEntity<InternalConfirmationDTO<String>> accountValidation(
          @RequestParam("email") String email) {
    var validation = accountService.validateEmailAddress(email);
    return ResponseEntity.ok(validation);
  }

  @GetMapping("/retrieve-user")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<InternalConfirmationDTO<MemberAccountDTO>> getUser(Authentication auth){
    accountService.findMemberAccount(auth.getName());
    return null;
  }

  @PostMapping( path = "/create-user", produces = {"application/json"})
  public ResponseEntity<InternalConfirmationDTO<MemberAccountDTO>> createUser(
          @Valid @RequestBody MemberAccountDTO newUserRegistration, BindingResult errorCheck)
  {
    System.out.println("EEJDSLKFJASLDFJSDLKFSF");
    if (errorCheck.hasErrors()) {
      throw new ValidationException("Information not valid", errorCheck );
    } else {
      var confirmationDTO = accountService.createNewUser(newUserRegistration);
        logger.info("User registration successful for " + newUserRegistration.email());
        return ResponseEntity.ok(confirmationDTO);
    }
  }

  @PutMapping(value = "update-account", produces = "application/json")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<InternalConfirmationDTO<MemberAccountDTO>> updateAccountInfo(
          @Valid @RequestBody MemberAccountDTO accountUpdate, BindingResult errorCheck, Authentication auth)
  {
    if (errorCheck.hasErrors()) {
      throw new ValidationException("Update failed", errorCheck);
    } else {
      var internalConfirmation = accountService.updateUserAccount(accountUpdate, String.valueOf(auth.getName()));
      return ResponseEntity.ok(internalConfirmation);
    }
  }

  @DeleteMapping("/delete-user")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public ResponseEntity<InternalConfirmationDTO<String>> deleteUser(Authentication auth){
    //REVIEW IF THE USER HAS PENDING MATTERS WITH THE PLATFORM LIKE BILLING OR OPEN REQUESTS
    accountService.deleteUserAccount(auth.getName());
    return ResponseEntity.ok(null);
  }


}

package com.myplace.usermanagement.services;

import com.myplace.usermanagement.entity.Users.Member;
import com.myplace.usermanagement.errors.ErrorTypes.DataBaseInteractionException;
import com.myplace.usermanagement.models.InternalConfirmationDTO;
import com.myplace.usermanagement.models.MemberAccountDTO;

import com.myplace.usermanagement.models.ResponseHttp;
import com.myplace.usermanagement.models.UserCredentials;
import com.myplace.usermanagement.repositories.AccountManagementDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImp {

  private final AccountManagementDAO accountManagementDAO;
  private final RestTemplate restTemplate;

  public InternalConfirmationDTO<String> validateEmailAddress(String email) {
    boolean re = accountManagementDAO.existsByEmail(email);
    String msg = re ? "User already exists" : "User was not found in the system";
    return new InternalConfirmationDTO<>(msg, re, email);
  }

  public InternalConfirmationDTO<Member> findMemberAccount(String uuid) {
    Optional<Member> member =
        Optional.of(accountManagementDAO.findByUserId(UUID.fromString(uuid)));
    return member
        .map(
            savedUser ->
                new InternalConfirmationDTO<>("User fetched successfully", true, savedUser))
        .orElseThrow(() -> new UsernameNotFoundException("User does not exist", null));
  }

  public InternalConfirmationDTO<MemberAccountDTO> createNewUser(MemberAccountDTO newUser){
    Member member = mapMemberAccountDtoToMember(newUser);
    UserCredentials userCredentials = mapMemberAccountDtoToUserCredentials(newUser);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<UserCredentials> requestEntity = new HttpEntity<>(userCredentials,headers);
    String targetEndpoint = "http://localhost:9001/create-usercredentials";

    try {
      ResponseEntity<ResponseHttp> response = restTemplate.exchange(targetEndpoint, HttpMethod.POST, requestEntity, ResponseHttp.class );
      if(!response.getBody().isOk()) {
        throw new DataBaseInteractionException("MEMBER_CREATE_FAILED");
      }
    }catch(RestClientException  | NullPointerException e){
      throw new DataBaseInteractionException("MEMBER_CREATE_FAILED");
    }
    Optional<Member> result = Optional.of(accountManagementDAO.save(member));
    return mapToMemberAccountDto(result, "MEMBER_CREATED_SUCCEFULLY", "MEMBER_CREATE_FAILED");

  }

  public InternalConfirmationDTO<MemberAccountDTO> updateUserAccount(
          MemberAccountDTO updateAccount, String uuid)  {

    Member userFetched = accountManagementDAO.findByUserId(UUID.fromString(uuid));
    userFetched.setFirstName(updateAccount.firstName() != null ? updateAccount.firstName(): userFetched.getFirstName());
    userFetched.setLastName(updateAccount.lastName() != null ? updateAccount.lastName() : userFetched.getLastName());
    userFetched.setEmail(updateAccount.email() != null ? updateAccount.email() : userFetched.getLastName());
    userFetched.setLastUpdated(LocalDateTime.now());

    Optional<Member> wasUpdated = Optional.ofNullable(accountManagementDAO.save(userFetched));

    return mapToMemberAccountDto(wasUpdated, "USER_UPDATE_SUCCEEDED", "MEMBER_UPDATE_FAILED");
  }

  public void deleteUserAccount(String uuid) {
    // Verify if the user has delinquent payments
    // verify the uuid coincides with the one from the cookie
    accountManagementDAO.deleteByUserId(UUID.fromString(uuid));
  }




  // ----------------------------------- UTILITY METHODS ---------------------------------

  private String updateIfExists(String oldValue, String newValue) {
    if (newValue != null && !newValue.equals("")) {
      return newValue;
    } else {
      return oldValue;
    }
  }

  private InternalConfirmationDTO<MemberAccountDTO> mapToMemberAccountDto(
      Optional<Member> member, String successMsg, String errorMsg)  {
    return member
        .map(
            savedMember ->
                new MemberAccountDTO(
                        savedMember.getUsername(),
                        savedMember.getFirstName(),
                        savedMember.getLastName(),
                        savedMember.getEmail(),
                        "PROTECTED",
                        Set.of()))
        .map(userDto -> new InternalConfirmationDTO<>(successMsg, true, userDto))
        .orElseThrow(() -> new DataBaseInteractionException(errorMsg));
  }

  private Member mapMemberAccountDtoToMember(MemberAccountDTO newUser){
          return  Member.builder()
                    .userId(UUID.randomUUID())
                    .username(newUser.username())
                    .email(newUser.email())
                    .firstName(newUser.firstName())
                    .lastName(newUser.lastName())
                    .build();
  }
  private UserCredentials mapMemberAccountDtoToUserCredentials(MemberAccountDTO newUser){
    return UserCredentials.builder()
            .email(newUser.email())
            .password(newUser.password())
            .roles(newUser.roles())
            .build();
  }
}

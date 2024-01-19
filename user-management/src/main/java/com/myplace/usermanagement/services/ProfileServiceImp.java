package com.myplace.usermanagement.services;

import com.myplace.usermanagement.entity.Users.Member;
import com.myplace.usermanagement.entity.Users.MemberInfo;
import com.myplace.usermanagement.errors.ErrorTypes.DataBaseInteractionException;
import com.myplace.usermanagement.models.InternalConfirmationDTO;
import com.myplace.usermanagement.models.MemberProfileDTO;
import com.myplace.usermanagement.repositories.AccountManagementDAO;
import com.myplace.usermanagement.repositories.ProfileManagementDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ProfileServiceImp {
    private final ProfileManagementDAO profileRepository;
    private final AccountManagementDAO accountRepository;

    public InternalConfirmationDTO<MemberProfileDTO> insertUpdateMemberInfo(String uuid, MemberProfileDTO updateProfile){
        //Retrieve the data
        Member member = accountRepository.findByUserId(UUID.fromString(uuid));//extract uuid from the security context
        MemberInfo memberInfo = member.getMemberInfo();
        memberInfo = MemberInfo.builder()
                .userId(member.getUserId())
                .bio(updateIfExists(memberInfo.getBio(), updateProfile.bio()))
                .city(updateIfExists(memberInfo.getCity(), updateProfile.city()))
                .pets(updateIfExists(memberInfo.getPets(), updateProfile.pets()))
                .state(updateIfExists(memberInfo.getState(), updateProfile.state()))
                .favoriteSong(updateIfExists(memberInfo.getFavoriteSong(), updateProfile.favoriteSong()))
                .profession(updateIfExists(memberInfo.getProfession(), updateProfile.profession()))
                .phoneNumber(updateIfExists(memberInfo.getPhoneNumber(), updateProfile.phoneNumber()))
                .dob(updateProfile.dob() != null ? updateProfile.dob() : memberInfo.getDob())
                .languagesStorage(updateProfile.languages() != null ?  updateProfile.languages():  memberInfo.getLanguagesStorage() )
                .interestsStorage(updateProfile.interests() != null ? updateProfile.interests() : memberInfo.getInterestsStorage())
                .sportsStorage(updateProfile.sports() != null ? updateProfile.sports() : memberInfo.getSportsStorage())
                .build();
        member.setMemberInfo(memberInfo);
        Member userUpdated = accountRepository.save(member);
        return mapToMemberProfileDto(userUpdated.getMemberInfo());
    }

    public InternalConfirmationDTO<MemberProfileDTO> getMemberInfo(String uuid){
        MemberInfo memberInfo = profileRepository.findByUserId(UUID.fromString(uuid));
        return mapToMemberProfileDto(memberInfo);

    }

    public InternalConfirmationDTO<MemberProfileDTO> processNaturalPersonVerification(String userId){
        return null;
    }


    // ---------------------------------- UTILITY METHODS ------------------------------------
    private InternalConfirmationDTO<MemberProfileDTO> mapToMemberProfileDto(
            MemberInfo memberInf)
    {
        return Optional.of(memberInf)
                .map(
                        memberInfo ->
         new MemberProfileDTO(
                memberInfo.getBio(),
                memberInfo.getCity(),
                memberInfo.getState(),
                memberInfo.getCountry(),
                memberInfo.getCuriousFactAboutMe(),
                memberInfo.getFavoriteSong(),
                memberInfo.getPets(),
                memberInfo.getProfession(),
                memberInfo.getStudies(),
                memberInfo.getWhatILove(),
                memberInfo.getInterestsStorage(),
                memberInfo.getSportsStorage(),
                memberInfo.getLanguagesStorage(),
                memberInfo.getDob(),
                memberInfo.getPhoneNumber()
        ))
                .map(memberInfoDto -> new InternalConfirmationDTO<>("User updated successfully", true, memberInfoDto))
                .orElseThrow(() -> new DataBaseInteractionException("Error updating user"));
    }
    private String updateIfExists(String oldValue, String newValue ){
        if(newValue != null && !newValue.equals("")){
            return newValue;
        }else{
            return oldValue;
        }
    }

}

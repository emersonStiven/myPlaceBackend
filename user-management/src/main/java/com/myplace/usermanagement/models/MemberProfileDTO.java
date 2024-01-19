package com.myplace.usermanagement.models;
import com.myplace.usermanagement.entity.EnumClasses.Interests;
import com.myplace.usermanagement.entity.EnumClasses.Languages;
import com.myplace.usermanagement.entity.EnumClasses.Sports;

import java.util.Date;
import java.util.Set;

public record MemberProfileDTO(
    String bio,
    String city,
    String state,
    String country,
    String curiousFact,
    String favoriteSong,
    String pets,
    String profession,
    String studies,
    String whatILove,
    Set<Interests> interests,
    Set<Sports> sports,
    Set<Languages> languages,
    Date dob,
    String phoneNumber){}

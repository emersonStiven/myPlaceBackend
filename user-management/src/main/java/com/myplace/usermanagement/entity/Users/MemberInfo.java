
package com.myplace.usermanagement.entity.Users;

import com.myplace.usermanagement.entity.EnumClasses.Interests;
import com.myplace.usermanagement.entity.EnumClasses.Languages;
import com.myplace.usermanagement.entity.EnumClasses.Sports;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "member_info",
        uniqueConstraints = {
                @UniqueConstraint(name = "UNIQUE_USERID", columnNames = "user_id")
        },
        indexes = {@Index(name = "INDEX_USERID", columnList = "user_id")}

)
public class MemberInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id",nullable = false, unique =true, updatable = false)
    private UUID userId;

    private String country;
    @Column(name = "phone_number", length = 20, unique = true)
    private String phoneNumber;
    private String state;
    private String city;
    private String studies;
    private String bio;
    @Column(name = "what_i_love")
    private String whatILove;
    @Column(name="curious_facts")
    private String curiousFactAboutMe;
    private String profession;
    @Column(name="favorite_song")
    private String favoriteSong;
    private String pets;
    private Date dob;
    @Embedded
    private MemberBookingHistory userDetails;

    @Convert(converter = ListToJsonConverter.class)
    @Column(name = "languages")
    @JdbcTypeCode (SqlTypes.JSON)
    private Set<Languages> languagesStorage;

    //@OneToMany(targetEntity = Sport.class, mappedBy = "userId",fetch = FetchType.EAGER)
    @Convert(converter = ListToJsonConverter.class)
    @Column(name = "sports")
    @JdbcTypeCode (SqlTypes.JSON)
    private Set<Sports> sportsStorage;

    @Convert(converter = ListToJsonConverter.class)
    @Column(name = "interests")
    @JdbcTypeCode (SqlTypes.JSON)
    private Set<Interests> interestsStorage;

    @OneToOne(targetEntity = NaturalPersonVerification.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
    public NaturalPersonVerification verificationNaturalPerson;

    public void addInterest(Interests i){
        if(!Objects.nonNull(interestsStorage)) interestsStorage = new HashSet<>();
        interestsStorage.add(i);
    }
    public void addSport(Sports s){
        if(!Objects.nonNull(sportsStorage)) sportsStorage = new HashSet<>();
        sportsStorage.add(s);
    }

    public void addLanguage(Languages l){
        if(!Objects.nonNull(languagesStorage)) languagesStorage = new HashSet<>();
        languagesStorage.add(l);
    }
}



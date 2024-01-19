package com.myplace.usermanagement.entity.Users;

import com.myplace.usermanagement.entity.EnumClasses.Gender;
import com.myplace.usermanagement.entity.EnumClasses.VerificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "verification_natural_person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NaturalPersonVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = MemberInfo.class, mappedBy = "verificationNaturalPerson")
    @JoinColumn(name = "user_id", unique = true, nullable = false, referencedColumnName = "userinfo_id")
    private MemberInfo user_id;
    @Lob
    @Column(name = "identify_proof", nullable = false)
    private byte[] identifyProf;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "second_name", nullable = true)
    private String secondName;
    @Column(name = "first_last_name", nullable = false)
    private String firstLastName;
    @Column(name = "second_last_name", nullable = true)
    private String secondLastName;
    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "verificationStatus", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private VerificationStatus verificationStatus;
    @Column(name = "verification_approval_data", nullable = true)
    private LocalDateTime verificationApprovalDate;

}



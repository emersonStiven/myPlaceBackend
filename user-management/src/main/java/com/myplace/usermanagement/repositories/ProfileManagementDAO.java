package com.myplace.usermanagement.repositories;

import com.myplace.usermanagement.entity.Users.MemberInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileManagementDAO extends JpaRepository<MemberInfo, Long> {

    MemberInfo findByUserId(UUID uuid);
    @Transactional
    MemberInfo save(MemberInfo memberInfo);
}

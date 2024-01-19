

package com.myplace.usermanagement.repositories;

//import com.myplace.usermanagement.models.Member.Member;
import com.myplace.usermanagement.entity.Users.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountManagementDAO extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    boolean existsByEmail(String email);
    Member save (Member user);
    void deleteByUserId(UUID id);
    Member findByUserId(UUID fromString);
}



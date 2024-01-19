package com.myplace.authorizationserver.repository;




import com.myplace.authorizationserver.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials,Long> {
    UserCredentials findByEmail(String email);
}

package emerson.sample.myPlace.Repositories;

import emerson.sample.myPlace.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    @Meta(comment = "search for occurrences of  email in the table")
    boolean existsByEmail(String email);

    Users save(Users users);
    Optional<Users> findByEmail(String email);

}

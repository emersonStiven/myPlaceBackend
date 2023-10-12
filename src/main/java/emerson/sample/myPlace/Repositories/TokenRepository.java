package emerson.sample.myPlace.Repositories;

import emerson.sample.myPlace.Entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token save(Token entity);
    Optional<Token> findByToken(String jwt);
}

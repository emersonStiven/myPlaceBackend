package emerson.sample.myPlace.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//@Slf4j
@Component
public class JwtUtils {

    //@Value("${jwt.secret}")
    private String secret = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    //@Value("${jwt.token.expiration")
    private String token_expiration="86400000";

    //@Value("${jwt.refresh.expiration}")
    private String refreshToken_expiration ="604800000";

    //WE DECIDED TO REPLACE THE USERDETAILS OBJECT FOR STRING OBJECTS IN THE METHODS
    // THAT GENERATE TOKENS AS A MEDIUM TO PASS THE EMAIL
    public String generateJwtToken(UserDetails details){
       return generateJwtToken(new HashMap<>(), details);
    }

    public String generateJwtToken(Map<String, Object> claims, UserDetails details){
        return buildJwtToken(claims, details, Long.parseLong(token_expiration));
    }
    public String generateRefreshToken(UserDetails details){
        return buildJwtToken(new HashMap<>(), details, Long.parseLong(refreshToken_expiration));
    }
    private String buildJwtToken(Map<String, Object>  claims, UserDetails details, long expiration){
        return Jwts.builder()
                .setSubject(details.getUsername())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() +  expiration))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getPrivateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getPrivateKey(){
        byte [] encodedValue = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(encodedValue);
    }

    // ----------------------- EXPLORE THE JWT TOKEN --------------------------

    public boolean isTokenValid(String token, UserDetails details) {
        return obtainClaims(token).getSubject().equals(details.getUsername()) && !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token){
       return obtainExpirationToken(token).before(new Date(System.currentTimeMillis()));
    }

    public Claims obtainClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getPrivateKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T obtainClaim(String token, Function<Claims, T> myFunc){
        final Claims claims = obtainClaims(token);
        return myFunc.apply(claims);
    }
    public String obtainEmail(String token){
        return  obtainClaim(token, (Claims c) -> c.getSubject() );
    }
    public Date obtainExpirationToken(String token){
        return obtainClaim(token, Claims::getExpiration);
    }



}

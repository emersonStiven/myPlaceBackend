package emerson.sample.myPlace.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import emerson.sample.myPlace.DTOs.AuthenticationRequest;
import emerson.sample.myPlace.Entities.EnumClasses.TokenType;
import emerson.sample.myPlace.Entities.Token;
import emerson.sample.myPlace.Repositories.TokenRepository;
import emerson.sample.myPlace.Repositories.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashMap;

@Component
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public JwtAuthenticationFilter(ManagerProvider mp, ObjectMapper om, JwtUtils jwt, UsersRepository userRepo, TokenRepository tokenRepository) {
        this.setFilterProcessesUrl("/login");
        this.managerProvider = mp;
        this.objectMapper = om;
        this.jwtUtils = jwt;
        this.usersRepository = userRepo;
        this.tokenRepository = tokenRepository;
    }

    private UsersRepository usersRepository;
    private TokenRepository tokenRepository;
    private ManagerProvider managerProvider;
    private ObjectMapper objectMapper;
    private JwtUtils jwtUtils;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        AuthenticationRequest authRequest = new AuthenticationRequest("", "");
        try {
            authRequest = objectMapper.readValue(request.getReader(), AuthenticationRequest.class);
        } catch (IOException e) {
            log.error("Something is wrong with the request body");
        }
        AuthenticationToken au = AuthenticationToken.builder()
                .authenticationRequest(authRequest)
                .isAuthenticated(false).build();
        return managerProvider.authenticate(au);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        AuthenticationToken au = (AuthenticationToken) auth;

        HashMap<String, Object> map = new HashMap<>();
        map.put("email", au.getUser().getEmail());
        map.put("Authorities", au.getUser().getAuthorities());

        String accessToken = jwtUtils.generateJwtToken(map, au.getUser());
        String refreshToken = jwtUtils.generateRefreshToken(au.getUser());

        //CREATING AND STORING THIS TOKEN HELPS US TRACK ALL TOKENS GRANTED
        //ALTHOUGH
        Token token = Token.builder()
                .user(au.getUser()).revoked(false)
                .expired(false).token(accessToken)
                .refreshToken(refreshToken)
                .type(TokenType.Bearer).build();
        tokenRepository.save(token);

        //WE INCLUDE THE ACCCESS AND REFRESH TOKENS + THE RESULT OF THE AUTHENTICATION PROCESS
        //IN A JSON INTO THE HTTPRESPONSE
        AuthenticationResponse authResponse = AuthenticationResponse.builder()
                .success(true).accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        response.getWriter().write(objectMapper.writeValueAsString(authResponse));

        //LET THE DEFAULT METHOD IMPLEMENTATION TAKE CARE OF SAVING THE AUTH IN THE CONTEXT
        super.successfulAuthentication(request, response, chain, au);

    }
}
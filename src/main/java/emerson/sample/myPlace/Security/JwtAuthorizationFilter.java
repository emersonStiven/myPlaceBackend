package emerson.sample.myPlace.Security;

import emerson.sample.myPlace.Entities.Users;
import emerson.sample.myPlace.Repositories.TokenRepository;
import emerson.sample.myPlace.Repositories.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         ;
        String header = request.getHeader("Authorization");
         if(header == null || !header.startsWith("Bearer ")){//Si no inicia con bearer, aca no tiene nada que hacer, autentiquese y envie el token en el header authorization
            filterChain.doFilter(request, response);
        }
        String token = header.substring(7);
        String userEmail = jwtUtils.obtainEmail(token);
        Users user = usersRepository.findByEmail(userEmail).orElseThrow(  () -> new UsernameNotFoundException("sdfsdf"));

        //REVISAR EL ACCESS TOKEN EN LA BASE DE DATOS THE VALIDITY OF THE TOKEN
        boolean isTokenValid = tokenRepository.findByToken(token).map(e -> !e.isRevoked() && !e.isExpired() ).orElse(false);

        //REVISAMOS SI EL ACCESS TOKEN NO HA EXPIRADO
        if(!jwtUtils.isTokenExpired(token) && jwtUtils.isTokenValid(token, user)){
            //LOAD AUTHENTICATION TOKEN IN THE SECURITY CONTEXT
            var au = AuthenticationToken.builder()
                    .isAuthenticated(true)
                    .user(user)
                    .request(request).build();
            SecurityContextHolder.createEmptyContext().setAuthentication(au);
        }
        filterChain.doFilter(request,response);

    }
}

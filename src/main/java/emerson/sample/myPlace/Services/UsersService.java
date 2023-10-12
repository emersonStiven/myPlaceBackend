package emerson.sample.myPlace.Services;

import emerson.sample.myPlace.DTOs.JwtToken;
import emerson.sample.myPlace.DTOs.RequestCreateUser;
import emerson.sample.myPlace.Entities.EnumClasses.TokenType;
import emerson.sample.myPlace.Entities.Token;
import emerson.sample.myPlace.Entities.Users;
import emerson.sample.myPlace.Repositories.TokenRepository;
import emerson.sample.myPlace.Repositories.UsersRepository;
import emerson.sample.myPlace.Security.JwtUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    @Autowired
    public TokenRepository tokenRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Value("${mail.username}")
    private String sender;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.port}")
    private String port;

    @Value("${mail.host}")
    private String host;

    public JwtToken createUser(RequestCreateUser requestCreateUser)  {
        //CREATE USER
        Users user = Users.builder()
                .firstName(requestCreateUser.getFirstName())
                .lastName(requestCreateUser.getLastName())
                .email(requestCreateUser.getEmail())
                .password(requestCreateUser.getPassword())
                .role(requestCreateUser.getRole())
                .build();

        //CREATE AND SAVE JWT TOKEN FOR FUTURE VALIDATIONS
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("email", requestCreateUser.getEmail());
        String jsonToken=  jwtUtils.generateJwtToken(claims, user);
        String jsonRefreshToken = jwtUtils.generateRefreshToken(user);
        Token token  =Token.builder()
                .type(TokenType.Bearer)
                .creationDate(LocalDateTime.now())
                .revoked(false)
                .expired(false)
                .user(user)
                .build();
        tokenRepository.save(token);

        //RETURN A JWT TOKEN AND A REFRESH TOKEN TO THE CLIENT
        return new JwtToken(jsonToken, jsonRefreshToken);
    }

    public Optional<Users> findEmailAddress(String email) {
        Optional<Users> user = usersRepository.findByEmail(email);
        return Optional.ofNullable(user.get());
    }

    public boolean validateEmailAddress(RequestCreateUser emailValidation) {
        if(!usersRepository.existsByEmail(emailValidation.getEmail())){
            return false;
        }
        String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        /* Inline styling for email format */
                        p {
                            font-weight: 600;
                            text-align: left;
                        }
                        .code{
                            color: #ff6262;
                            font-weight: 700;
                        }
                        h4{
                            font-weight:700;
                        }
                        .title-container{
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            background-color: black;
                        }
                                
                    </style>
                </head>
                <body>
                    <div>
                        <div class="title-container"><h2>MyPlace</h2></div>
                        <h4>Login Verification</h4>
                        <p>Your verification code</p>
                        <p>This verification code will be valid for 30 minutes. Please do not share this code with anyone.</p>
                        <p>This is an automated message, please do not reply</p>
                    </div>
                </body>
                </html>
                                
                """;


        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true, "UTF-8");
            mimeMessageHelper.setTo(sender);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Email verification ");
        }catch(Exception e){
            e.printStackTrace();
        }
        mailSender.send(mimeMailMessage);
        return true;
    }


}

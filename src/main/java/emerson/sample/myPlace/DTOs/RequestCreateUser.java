package emerson.sample.myPlace.DTOs;

import emerson.sample.myPlace.Entities.EnumClasses.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateUser {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private Role role;

}

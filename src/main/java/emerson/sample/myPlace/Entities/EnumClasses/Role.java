package emerson.sample.myPlace.Entities.EnumClasses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import static emerson.sample.myPlace.Entities.EnumClasses.Permission.ADMIN_CREATE;
import static emerson.sample.myPlace.Entities.EnumClasses.Permission.ADMIN_DELETE;
import static emerson.sample.myPlace.Entities.EnumClasses.Permission.ADMIN_READ;
import static emerson.sample.myPlace.Entities.EnumClasses.Permission.ADMIN_UPDATE;
import static emerson.sample.myPlace.Entities.EnumClasses.Permission.MANAGER_CREATE;
import static emerson.sample.myPlace.Entities.EnumClasses.Permission.MANAGER_DELETE;
import static emerson.sample.myPlace.Entities.EnumClasses.Permission.MANAGER_READ;
import static emerson.sample.myPlace.Entities.EnumClasses.Permission.MANAGER_UPDATE;
@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    )

    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions().stream()
                .map(e -> new SimpleGrantedAuthority(e.getPermission())).toList();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}

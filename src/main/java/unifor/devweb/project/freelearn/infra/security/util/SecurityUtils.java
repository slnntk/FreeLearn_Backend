package unifor.devweb.project.freelearn.infra.security.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import unifor.devweb.project.freelearn.domain.entities.user.User;
import unifor.devweb.project.freelearn.repository.UserRepository;

@Component
@Log4j2
@RequiredArgsConstructor
public class SecurityUtils {

    @Value("${user.unauthenticated}")
    private String UNAUTHENTICATED_USER;

    private final UserRepository userRepository;
    private Authentication authentication;

    public boolean isAuthenticated() {
        getAuthenticate();
        return authentication != null && authentication.isAuthenticated() && !UNAUTHENTICATED_USER.equals(authentication.getName());
    }

    public boolean isAdmin() {
        return authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public User authenticatedUser() {
        isAuthenticated();
        return userRepository.findByEmail(authentication.getName());
    }

    public void getAuthenticate() {
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
    }
}

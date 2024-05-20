package unifor.devweb.project.freelearn.controller.security;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.service.SecurityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unifor.devweb.project.freelearn.domain.entities.user.User;
import unifor.devweb.project.freelearn.domain.entities.user.UserRole;
import unifor.devweb.project.freelearn.dto.security.AuthenticationDTO;
import unifor.devweb.project.freelearn.dto.security.LoginResponseDTO;
import unifor.devweb.project.freelearn.dto.security.RegisterDTO;
import unifor.devweb.project.freelearn.exception.AccessDeniedException;
import unifor.devweb.project.freelearn.exception.BadRequestException;
import unifor.devweb.project.freelearn.infra.security.SecurityFilter;
import unifor.devweb.project.freelearn.infra.security.TokenService;
import unifor.devweb.project.freelearn.repository.UserRepository;

@RestController
@Log4j2
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final SecurityFilter securityFilter;

    @PostMapping("/login")
    public ResponseEntity performLogin(@RequestBody @Valid AuthenticationDTO authenticationData) {
        String token = null;
        try {
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationData.email(), authenticationData.password());
            var authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            token = tokenService.generateToken((User) authentication.getPrincipal());
        } catch (Exception e) {
            log.error("error");
        }
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity performRegistration(@RequestBody @Valid RegisterDTO registrationData) {

        if (this.userRepository.findByEmail(registrationData.email()) != null) return ResponseEntity.badRequest().build();

        RegisterDTO processedRegistrationData = processRegistrationData(registrationData);
        String encryptedPassword = new BCryptPasswordEncoder().encode(processedRegistrationData.password());
        User newUser = new User(processedRegistrationData.email(), processedRegistrationData.name(), encryptedPassword, processedRegistrationData.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    private RegisterDTO processRegistrationData(RegisterDTO registrationData) {

        if (!securityFilter.isAdmin() && UserRole.ADMIN.equals(registrationData.role())) {
            throw new AccessDeniedException("You do not have permission to create this user");
        }

        return registrationData;
    }
}

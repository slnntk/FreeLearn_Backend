package unifor.devweb.project.freelearn.controller.security;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unifor.devweb.project.freelearn.domain.entities.user.User;
import unifor.devweb.project.freelearn.dto.security.AuthenticationDTO;
import unifor.devweb.project.freelearn.dto.security.LoginResponseDTO;
import unifor.devweb.project.freelearn.dto.security.RegisterDTO;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        String token = null;
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            token = tokenService.generateToken((User)auth.getPrincipal());
        } catch (Exception e){
            log.error("error");
        }
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), data.name(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}

package unifor.devweb.project.freelearn.dto.security;

import unifor.devweb.project.freelearn.domain.entities.user.UserRole;

public record RegisterDTO (String email, String name ,String password, UserRole role) {
}

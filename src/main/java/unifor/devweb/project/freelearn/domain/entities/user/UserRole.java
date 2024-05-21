package unifor.devweb.project.freelearn.domain.entities.user;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    TEACHER("teacher");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
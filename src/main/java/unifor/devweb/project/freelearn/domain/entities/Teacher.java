package unifor.devweb.project.freelearn.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import unifor.devweb.project.freelearn.domain.entities.user.User;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;

    @ElementCollection
    private List<String> areasOfExpertise;

    private double overallRating;
    private int hoursTaught;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", employeeId='" + employeeId + '\'' +
                ", areasOfExpertise=" + areasOfExpertise +
                ", overallRating=" + overallRating +
                ", hoursTaught=" + hoursTaught +
                '}';
    }
}

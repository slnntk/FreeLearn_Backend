package unifor.devweb.project.freelearn.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}

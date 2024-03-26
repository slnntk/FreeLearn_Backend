package unifor.devweb.project.freelearn.domain.entities;

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

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    private String employeeId;
    private List<String> areasOfExpertise;
    private double overallRating;
    private int hoursTaught;
}


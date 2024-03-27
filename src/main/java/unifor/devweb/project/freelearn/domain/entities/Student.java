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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int hoursWatched;
    private int numberOfCoursesSubscribed;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "student")
    private List<Review> reviews;

    @ManyToMany
    private List<Course> enrolledCourses;
}

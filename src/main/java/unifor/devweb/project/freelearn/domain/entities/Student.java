package unifor.devweb.project.freelearn.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "student")
    private List<Review> reviews;

    @ManyToMany(mappedBy = "enrolledStudents")
    private List<Course> enrolledCourses;


    private int hoursWatched;
    private int numberOfCoursesSubscribed;
}
package unifor.devweb.project.freelearn.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int hoursWatched;
    private int numberOfCoursesSubscribed;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "student")
    private List<Review> reviews;

    @OneToMany(mappedBy = "student")
    private List<StudentCourse> enrolledCourses;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", hoursWatched=" + hoursWatched +
                ", numberOfCoursesSubscribed=" + numberOfCoursesSubscribed +
                '}';
    }
}
